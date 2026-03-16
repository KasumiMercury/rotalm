package net.mercuryksm.rotalm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import net.mercuryksm.rotalm.di.appModule
import org.koin.compose.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.koinConfiguration
import org.koin.dsl.module as koinModule

private val SheetVerticalPadding = 12.dp
private val SheetHorizontalPadding = 16.dp
private val SheetContentSpacing = 16.dp
private val DragHandleHeight = 48.dp
private val ButtonHeight = 40.dp
private val TextLineHeight = 24.dp
private val SheetPeekHeight: Dp =
    SheetVerticalPadding +
            DragHandleHeight +
            SheetContentSpacing +
            TextLineHeight +
            SheetContentSpacing +
            ButtonHeight +
            SheetVerticalPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(platformModule: Module = koinModule {}) {
    KoinApplication(configuration = koinConfiguration {
        modules(platformModule, appModule)
    }) {
    MaterialTheme {
        var selectedCardIndex: Int? by remember { mutableStateOf(null) }
        val scope = rememberCoroutineScope()
        val sheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
        val scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = sheetState,
        )

        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                BottomSheetContent(
                    cardIndex = selectedCardIndex ?: 0,
                )
            },
            sheetPeekHeight = SheetPeekHeight,
        ) { innerPadding ->
            CardGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onCardClick = { index ->
                    selectedCardIndex = index
                    scope.launch { sheetState.partialExpand() }
                },
            )
        }
    }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(
    index: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Card $index")
        }
    }
}

@Composable
fun CardGrid(
    modifier: Modifier = Modifier,
    onCardClick: (Int) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(6) { index ->
            CardItem(
                index = index,
                onClick = { onCardClick(index) },
            )
        }
    }
}

@Composable
fun BottomSheetContent(
    cardIndex: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(horizontal = SheetHorizontalPadding, vertical = SheetVerticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SheetContentSpacing),
    ) {
        Text(
            text = "Card $cardIndex",
            modifier = Modifier.height(TextLineHeight),
        )

        Button(
            onClick = {},
            modifier = Modifier.height(ButtonHeight),
        ) {
            Text("Action A")
        }

        Spacer(modifier = Modifier.height(SheetContentSpacing))

        Button(onClick = {}) {
            Text("Action B (expanded only)")
        }
    }
}

@Preview
@Composable
private fun CardItemPreview() {
    MaterialTheme {
        CardItem(index = 0, onClick = {})
    }
}

@Preview
@Composable
private fun BottomSheetContentPartialPreview() {
    MaterialTheme {
        BottomSheetContent(cardIndex = 2)
    }
}

@Preview
@Composable
private fun BottomSheetContentExpandedPreview() {
    MaterialTheme {
        BottomSheetContent(cardIndex = 2)
    }
}

@Preview
@Composable
private fun CardGridPreview() {
    MaterialTheme {
        CardGrid(modifier = Modifier.fillMaxSize())
    }
}

@Preview
@Composable
private fun AppPreview() {
    App()
}
