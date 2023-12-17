package com.toddkushnerllc.com.android_graph_app

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitTouchSlopOrCancellation
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.AwaitPointerEventScope
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.GestureDetectorCompat
import com.smarttoolfactory.composedrawingapp.DrawingApp
import com.toddkushnerllc.com.android_graph_app.ui.theme.AndroidgraphappTheme
import com.toddkushnerllc.diagram.Diagram

private const val MAIN_TAG = "MainActivity"

class MainActivity : ComponentActivity()
{
    private var diagram = Diagram()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diagram.holds.add(1, 100.0F, (1000.0F - 650.0F))
        diagram.holds.add(2, 350.0F, (1000.0F - 200.0F))
        diagram.holds.add(3, 700.0F, (1000.0F - 400.0F))
        diagram.holds.add(4, 1200.0F, (1000.0F - 400.0F))
        diagram.holds.add(5, 900.0F, (1000.0F - 350.0F))
        diagram.holds.add(6, 900.0F, (1000.0F - 200.0F))
        diagram.holds.add(7, 1300.0F, (1000.0F - 300.0F))
        diagram.holds.add(8, 1400.0F, (1000.0F - 200.0F))
        diagram.holds.add(9, 1800.0F, (1000.0F - 250.0F))
        diagram.holds.add(10, 1400.0F, (1000.0F - 800.0F))
        diagram.holds.add(11, 1900.0F, (1000.0F - 750.0F))

        diagram.edges.add(1, 1, 2, diagram.holds)
        diagram.edges.add(2, 1, 3, diagram.holds)
        diagram.edges.add(3, 1, 10, diagram.holds)
        diagram.edges.add(4, 2, 3, diagram.holds)
        diagram.edges.add(5, 2, 5, diagram.holds)
        diagram.edges.add(6, 2, 6, diagram.holds)
        diagram.edges.add(7, 3, 5, diagram.holds)
        diagram.edges.add(8, 4, 5, diagram.holds)
        diagram.edges.add(9, 4, 7, diagram.holds)
        diagram.edges.add(10, 4, 9, diagram.holds)
        diagram.edges.add(11, 4, 10, diagram.holds)
        diagram.edges.add(12, 5, 7, diagram.holds)
        diagram.edges.add(13, 6, 8, diagram.holds)
        diagram.edges.add(14, 7, 9, diagram.holds)
        diagram.edges.add(15, 8, 9, diagram.holds)
        diagram.edges.add(16, 9, 11, diagram.holds)
        diagram.edges.add(17, 10, 11, diagram.holds)

        setContent {
            AndroidgraphappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DrawingApp(diagram)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(MAIN_TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(MAIN_TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(MAIN_TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(MAIN_TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(MAIN_TAG, "onDestroy() called")
    }

}
