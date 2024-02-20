package com.smarttoolfactory.composedrawingapp

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.toddkushnerllc.diagram.Diagram

/**
 * DrawingApp is the primary driver for the app
 */
@Composable
fun DrawingApp(diagram: Diagram) {
    val state: MutableState<Boolean> = remember { mutableStateOf(false) }

    /**
     * LogGesture - detects whether a tap or drag end touches a diagram edge or hold
     * or a swipe is parallel to an edge
     */
    val TAG = "tag"
    fun LogGesture(label: String, action: String, start: Offset?, end: Offset?) {
        Log.d(TAG, "$label $action from $start to $end")
        if (start != null && end != null)
            if (diagram.touched(start, end)) state.value = true
    }

    var press: Offset? = null
    var lastDrag: Offset? = null

    // modifier to pick up both taps and swipes/drags
    val drawModifier = Modifier
        .padding(8.dp)
        .shadow(1.dp)
        .fillMaxWidth()
        .background(Color.White)
        // https://developer.android.com/jetpack/compose/touch-input/pointer-input/understand-gestures
        // https://developer.android.com/jetpack/compose/touch-input/pointer-input
        // https://developer.android.com/reference/kotlin/androidx/compose/foundation/gestures/package-summary#(androidx.compose.ui.input.pointer.PointerInputScope).detectDragGestures(kotlin.Function1,kotlin.Function0,kotlin.Function0,kotlin.Function2)
        .pointerInput(Unit) {
            detectDragGestures(
                onDragStart = { Log.d(TAG, "detectDragGestures onDragStart $it") },
                onDragEnd = {
                    Log.d(TAG, "detectDragGestures onDragEnd")
                    LogGesture("detectDragGestures", "drag", press, lastDrag)
                },
                onDragCancel = { Log.d(TAG, "detectDragGestures onDragCancel") },
                onDrag = { change, dragAmount ->
                    lastDrag = change.position
                    Log.d(
                        TAG,
                        "detectDragGestures onDrag change.position ${change.position}"
                    )
                }
            )
        }
        .pointerInput(Unit) {
            detectVerticalDragGestures(
                onDragStart = { Log.d(TAG, "detectVerticalDragGestures onDragStart $it") },
                onDragEnd = {
                    Log.d(TAG, "detectVerticalDragGestures onDragEnd")
                    LogGesture("detectVerticalDragGestures", "vertical drag", press, lastDrag)
                },
                onDragCancel = { Log.d(TAG, "detectVerticalDragGestures onDragCancel") },
                onVerticalDrag = { change, dragAmount ->
                    lastDrag = change.position
                    Log.d(
                        TAG,
                        "detectVerticalDragGestures onVerticalDrag change.position ${change.position}"
                    )
                }
            )
        }
        .pointerInput(Unit) {
            detectHorizontalDragGestures(
                onDragStart = { Log.d(TAG, "detectHorizontalDragGestures onDragStart $it") },
                onDragEnd = {
                    Log.d(TAG, "detectHorizontalDragGestures onDragEnd")
                    LogGesture("detectHorizontalDragGestures", "horizontal drag", press, lastDrag)
                },
                onDragCancel = { Log.d(TAG, "detectHorizontalDragGestures onDragCancel") },
                onHorizontalDrag = { change, dragAmount ->
                    lastDrag = change.position
                    Log.d(
                        TAG,
                        "detectHorizontalDragGestures onHorizontalDrag change.position ${change.position}"
                    )
                }
            )
        }
        .pointerInput(Unit) {
            detectDragGesturesAfterLongPress(
                onDragStart = {
                    lastDrag = it
                    Log.d(TAG, "detectDragGesturesAfterLongPress onDragStart $it")
                },
                onDragEnd = {
                    Log.d(TAG, "detectDragGesturesAfterLongPress onDragEnd")
                },
                onDragCancel = {
                    Log.d(TAG, "detectDragGesturesAfterLongPress onDragCancel")
                    LogGesture("detectDragGesturesAfterLongPress", "long press", press, lastDrag)
                },
                onDrag = { change, dragAmount ->
                    Log.d(
                        TAG,
                        "detectDragGesturesAfterLongPress change.position ${change.position}"
                    )
                }
            )
        }
        .pointerInput(Unit) {
            // https://developer.android.com/reference/kotlin/androidx/compose/foundation/gestures/package-summary#(androidx.compose.ui.input.pointer.PointerInputScope).detectTapGestures(kotlin.Function1,kotlin.Function1,kotlin.coroutines.SuspendFunction2,kotlin.Function1)
            detectTapGestures(
                onTap = {
                    Log.d(TAG, "detectTapGestures onTap $it")
                    LogGesture("detectTapGestures", "tap", press, it)
                },
                onDoubleTap = {
                    Log.d(TAG, "detectTapGestures onDoubleTap $it")
                    LogGesture("detectTapGestures", "double tap", press, it)
                },
                onLongPress = {
                    Log.d(TAG, "detectTapGestures onLongPress $it")
                },
                onPress = {
                    press = it
                    Log.d(TAG, "detectTapGestures onPress $it")
                }
            )
        }
    // redraws diagram after selecting a node
    Canvas(modifier = drawModifier) {
        state.value?.let {
            state.value = false
            val canvas = drawContext.canvas.nativeCanvas
            diagram.holds.hold_map.forEach {
                it.value.draw(canvas)
            }
            diagram.edges.edge_set.forEach {
                it.draw(canvas, diagram.holds)
            }
        }
    }
}


