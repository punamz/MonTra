package com.punam.montra.src.presentation.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.DefaultAlpha
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.punam.montra.src.presentation.component.ErrorData
import com.punam.montra.src.presentation.component.Loading
import com.punam.montra.util.FrequencyType
import com.punam.montra.util.UiText
import com.punam.montra.util.toDayOfWeekStringRes
import com.punam.montra.util.toMonthStringRes


@Composable
fun TransactionChart(type: FrequencyType, datasetForModel: List<FloatEntry>, isLoading: Boolean) {

    val context = LocalContext.current
    val datasetLineSpec = arrayListOf(
        LineChart.LineSpec(
            lineColor = MaterialTheme.colorScheme.primary.toArgb(),
            lineThicknessDp = 5f,
            lineBackgroundShader = DynamicShaders.fromBrush(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary.copy(DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                        MaterialTheme.colorScheme.primary.copy(DefaultAlpha.LINE_BACKGROUND_SHADER_END)
                    )
                )
            )
        )
    )
    val scrollState = rememberChartScrollState()
    val model = ChartEntryModelProducer(datasetForModel)
    val marker = rememberMarker()
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading)
            Box(
                modifier = Modifier
                    .size(150.dp)
            ) {
                Loading()
            }
        else
            if (datasetForModel.isEmpty())
                Box(
                    modifier = Modifier
                        .size(150.dp)
                ) {
                    ErrorData()
                }
            else
                ProvideChartStyle {
                    Chart(
                        chart = LineChart(
                            lines = datasetLineSpec,
                            spacingDp = when (type) {
                                FrequencyType.Month -> 45f
                                FrequencyType.Today -> 70f
                                FrequencyType.Week -> 60f
                                FrequencyType.Year -> 80f
                            }
                        ),
                        chartModelProducer = model,
                        chartScrollState = scrollState,
                        isZoomEnabled = true,
                        marker = marker,
                        bottomAxis = rememberBottomAxis(
                            tickLength = 0.dp,
                            valueFormatter = { value, _ ->
                                when (type) {
                                    FrequencyType.Month -> "${value.toInt() + 1} "
                                    FrequencyType.Today -> "${
                                        String.format(
                                            "%02d",
                                            value.toInt()
                                        )
                                    }:00"

                                    FrequencyType.Week -> UiText.StringResource(
                                        value.toInt().toDayOfWeekStringRes()
                                    ).asString(context)

                                    FrequencyType.Year -> UiText.StringResource(
                                        value.toInt().toMonthStringRes()
                                    ).asString(context)
                                }
                            },
                        ),
                    )
                }
    }
}

