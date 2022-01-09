package nl.birdly.graph.ui.chart.linechart

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

interface LineChartColors {

    @Composable
    fun lineColor(): State<Color>

    @Composable
    fun dotColor(): State<Color>

    @Composable
    fun graphColor(): State<Color>

    @Composable
    fun backgroundColor(): State<Color>
}

interface LineChartSizes {

    @Composable
    fun lineThickness(): State<Float>

    @Composable
    fun dotSize(): State<Size>
}

object LineChartDefaults {

    @Composable
    fun colors(
        lineColor: Color = MaterialTheme.colors.primary,
        dotColor: Color = MaterialTheme.colors.secondary,
        graphColor: Color = MaterialTheme.colors.primaryVariant,
        backgroundColor: Color = MaterialTheme.colors.background,
    ): LineChartColors = DefaultLineChartColors(
        lineColor,
        dotColor,
        graphColor,
        backgroundColor
    )

    @Composable
    fun sizes(
        lineThickness: Float = Stroke.DefaultMiter,
        dotSize: Size = Size(16.dp.value, 16.dp.value),
    ): LineChartSizes = DefaultLineChartSizes(
        lineThickness,
        dotSize,
    )
}

@Immutable
private class DefaultLineChartColors(
    private val lineColor: Color,
    private val dotColor: Color,
    private val graphColor: Color,
    private val backgroundColor: Color,
) : LineChartColors {

    @Composable
    override fun lineColor(): State<Color> {
        return rememberUpdatedState(newValue = lineColor)
    }

    @Composable
    override fun dotColor(): State<Color> {
        return rememberUpdatedState(newValue = dotColor)
    }

    @Composable
    override fun graphColor(): State<Color> {
        return rememberUpdatedState(newValue = graphColor)
    }

    @Composable
    override fun backgroundColor(): State<Color> {
        return rememberUpdatedState(newValue = backgroundColor)
    }
}

@Immutable
private class DefaultLineChartSizes(
    private val lineThickness: Float,
    private val dotSize: Size,
) : LineChartSizes {

    @Composable
    override fun lineThickness(): State<Float> {
        return rememberUpdatedState(newValue = lineThickness)
    }

    @Composable
    override fun dotSize(): State<Size> {
        return rememberUpdatedState(newValue = dotSize)
    }
}