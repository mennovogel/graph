package nl.birdly.graph.ui.chart.linechart

import android.graphics.PointF
import android.util.Log
import android.util.Range
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

@Composable
fun LineChart(
    modifier: Modifier,
    initialXRange: Range<Float>,
    yRange: Range<Float>,
    data: List<PointF>,
    colors: LineChartColors = LineChartDefaults.colors(),
    sizes: LineChartSizes = LineChartDefaults.sizes(),
) {
    val lineColor = colors.lineColor().value
    val dotColor = colors.dotColor().value
    val backgroundColor = colors.backgroundColor().value

    val lineThickness = sizes.lineThickness().value
    val dotSize = sizes.dotSize().value

    var lastDelta: Float? by remember { mutableStateOf(0f) }
    var xRange by remember { mutableStateOf(initialXRange) }
    var multiplier: Float? by remember { mutableStateOf(null) }

    Box(
        Modifier.scrollable(
            orientation = Orientation.Horizontal,
            state = rememberScrollableState { delta ->
                multiplier?.let { multiplier ->
                    lastDelta = delta / multiplier
                }
                delta
            }
        )
    ) {
        Canvas(
            modifier,
        ) {
            val overallXRange = Range.create(
                data.minOf { it.x },
                data.maxOf { it.x } - initialXRange.diff()
            )

            if (multiplier == null) {
                multiplier = size.width / initialXRange.diff()
            }
            lastDelta?.let { delta ->
                val newXRange = Range.create(
                    overallXRange.clamp(xRange.lower - delta),
                    overallXRange.clamp(xRange.lower - delta) + initialXRange.diff()
                )
                if (!newXRange.equals(xRange)) {
                    xRange = newXRange
                }
                lastDelta = null
            }

            drawRect(backgroundColor, size = size)
            drawLine(xRange, yRange, data, lineColor, lineThickness)
            data.forEach { point ->
                drawOval(xRange, yRange, point, dotColor, dotSize)
            }
        }
    }
}

private fun Range<Float>.diff() = upper - lower

private fun DrawScope.drawOval(
    xRange: Range<Float>,
    yRange: Range<Float>,
    point: PointF,
    color: Color,
    dotSize: Size,
) {
    val xRelativePosition = point.x.toRelativePosition(xRange)
    val yRelativePosition = point.y.toRelativePosition(yRange)

    drawOval(
        color,
        topLeft = Offset(
            size.width * xRelativePosition - (dotSize.width / 2f),
            size.height * (1f - yRelativePosition) - (dotSize.height / 2f)
        ),
        size = dotSize
    )
}

/**
 * RelativePositions range from 0.0 to 1.0 if they exist within the ranges.
 */
private fun Float.toRelativePosition(range: Range<Float>): Float {
    return (this - range.lower) / (range.upper - range.lower)
}

fun DrawScope.drawLine(
    xRange: Range<Float>,
    yRange: Range<Float>,
    data: List<PointF>,
    color: Color,
    thickness: Float,
) {
    data.zipWithNext().forEach { pair ->
        val startXRelativePosition = pair.first.x.toRelativePosition(xRange)
        val startYRelativePosition = pair.first.y.toRelativePosition(yRange)

        val endXRelativePosition = pair.second.x.toRelativePosition(xRange)
        val endYRelativePosition = pair.second.y.toRelativePosition(yRange)

        drawLine(
            color,
            start = Offset(
                startXRelativePosition * size.width,
                (1f - startYRelativePosition) * size.height
            ),
            end = Offset(
                endXRelativePosition * size.width,
                (1f - endYRelativePosition) * size.height
            ),
            strokeWidth = thickness,
        )
    }
}


