package com.example.app_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app_test.ui.theme.APP_testTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.focus.focusModifier
import org.intellij.lang.annotations.PrintFormat
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Button
import androidx.compose.material3.Shapes
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.modifier.modifierLocalOf


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APP_testTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MyAPP_2(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val expanded= remember { mutableStateOf(false) }
    val extraPadding =if(expanded.value)48.dp else 0.dp
    Surface(color =MaterialTheme.colorScheme.primary, modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Row (modifier=Modifier.padding(24.dp)){
            Column(modifier=Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "hello")
                Text(name)
            }
            ElevatedButton(onClick = {expanded.value=!expanded.value}) {
                Text(if (expanded.value)"Show less" else "Show more")
            }

        }
    }
}


@Composable
fun GreetingPreview() {
    APP_testTheme {
        Myapp()
    }
}
@Preview(showBackground = true, name = "text preview", widthDp = 320)
@Composable
fun Myapp(modifier: Modifier=Modifier,names:List<String> = listOf("Rynlemon","zhk")){
    Column(modifier=modifier.padding(vertical = 4.dp)) {
        for (name in names){
            Greeting(name = name)
        }
    }
}

//这往下是开局点击continue界面的设置
@Composable
fun MyAPP_2(modifier: Modifier= Modifier ){
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    Surface(modifier){
        if (shouldShowOnboarding)
        {
            /*相当于向下传递了一个操作，这个操作内容是{shouldShowOnboarding = false},但是他不是直白的，
            而是用onContinueClicked这个操作来实现传递给子组合函数
            状态向下流动
            事件向上传播 */
            OnboardingScreen(onContinueClicked={shouldShowOnboarding = false})
        }
        else
        {
            Myapp()
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit,modifier: Modifier=Modifier){
    Column (
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome")
        Button(
            modifier = modifier.padding(vertical =24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun showboarding(){
    APP_testTheme {
        OnboardingScreen(onContinueClicked={})
    }
}

