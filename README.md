<h1>MVI Sample App</h1>
<p>
This sample app demonstrates the modern Android architecture pattern - MVI(Model, View, Intent) using LiveData, Coroutines and Retrofit. When the user presses the button on the main screen, the app requests for user datas and displays them in a recyclerView.
</p>
<p align="center">
  <img src="https://user-images.githubusercontent.com/57670625/227792457-50104c7b-b626-4e62-9769-26c3875040a3.gif" width="23%"/>
</p>
<br>

<!-- Tech Stack -->
<h2>Tech Stack</h2>
<ul>
<li>Minumum SDK Level: 21</li>
<li>100% Kotlin</li>
<li>Architecture
    <ul>
      <li>MVI Pattern: Recommended Android architectural pattern works based on the unidirectional and cyclical flow of user interactions</li>
    </ul>
 </li>
<li>ViewModel: Exposes data streams as a state holder</li>
<li>Lifecycle: Observes Android lifecycles and handle operations to a change in the lifecycle status</li>
<li><a href="https://developer.android.com/kotlin/coroutines">Coroutines</a>: Concurrency design pattern provided by Kotlin</li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a>: Type-safe REST client for Android, Java and Kotlin developed by Square.  </li>
<li><a href="https://square.github.io/okhttp/">OkHttp</a> : 3rd party library sending and receive HTTP-based network requests built on top of the Okio library</li>
<li><a href="https://github.com/google/gson">GSON</a>: Java library that can be used to convert Java Objects into their JSON representation</li>
<li><a href="https://github.com/bumptech/glide">Glide</a>: Fast and efficient open source media management and image loading framework </li>
</ul>
<br>

<!-- MVI Description -->
<h2>What is MVI pattern?</h2>
<p>MVI stands for Model-View-Intent. Without using boilerplate callback functions, we can build the app reactive to user interactions. The main idea of this pattern is that View and Model communicates via "Intent" and "State".</p>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/227793219-0372bcbc-107d-4e27-b2cf-d38f7d3bf5fc.jpg" width="85%"/>
</p>
<ul>
<li><b>Model</b>: Model is the single source of the truth, which manages the states of the app.</li>
<li><b>View</b>: View represents UI layer including activity and fragments. View displays user interfaces and produces action and renders the state emitted by Model.</li>
<li><b>Intent</b>: This is not an Android traditional Intent. Intent is a performed action by user side of the app itself.</li>
</ul>
<br>

<h3>MVI flow diagram</h3>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/230803371-473163cf-6c5b-4796-a5d4-3683b4bd5acb.jpg" width="65%"/>
</p>
<p>This is the cyclical flow of MVI pattern. View defines an intent from an user interaction and delivers it to Model. Model defines a state following by the user interaction and notifiew View to update UIs to display the result of the user interaction.</p>

<!-- App Architecture -->
<h2>App Architecture</h2>
<p>This sample app was built with the modern app architecture - MVI pattern. MVI has the same structure with MVVM pattern. By separating multiple app components into two layers
- UI and Data, the app is scalable, maintainable and testable.</p>
<ul>
  <li>Architectural Principles</li>
    <ul>
      <li>Separations of concerns</li>
      <li>Drive UI from data models</li>
      <li>Single source of truth</li>
      <li>Unidirectional Data Flow</li>
   </ul>
</ul>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/228396945-a6f69b5d-730a-4ce4-b1b3-bd4f24124070.jpg" width="85%"/>
</p>
<h3>How MVI works in this app</h3>
<p>The below diagram presents how the app responds according to an user interaction.</p>

<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/230802837-9230fa69-6e3d-428a-8fe7-2d836d9bad1d.jpg" width="50%"/>
</p>

<p>1. The user clicks on the "Update data" button on the main screen. Then, View delivers "Intent(User action)" to ViewModel.</p>
<pre><code>binding.apply {
            btnUpdateData.setOnClickListener {
                // Send intent to viewModel
                viewModel.sendIntent(MainIntent.FetchUser)
            }
        }</code></pre>
<p>ViewModel receives Intent from View and send it to "userIntent" channel.</p>
<pre><code>fun sendIntent(intent: MainIntent) {
        viewModelScope.launch {
            userIntent.send(intent)
        }
    }</code></pre>
<p></p>

<p>2. ViewModel performs action(requesting data to repository).</p>
<pre><code>private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUser -> fetchUser()
                }
            }
        }
    }</code></pre>
    
<!-- References -->
<h2>References</h2>
<p>The purpose of this project was to understand the core principles of the MVI pattern. You can check out more information about MVI in following links.</p>
<li><a href="https://proandroiddev.com/mvi-architecture-with-kotlin-flows-and-channels-d36820b2028d">MVI Architecture with Kotlin Flows and Channels</a></li>
<li><a href="https://blog.mindorks.com/mvi-architecture-android-tutorial-for-beginners-step-by-step-guide/">MMVI Architecture - Android Tutorial for Beginners - Step By Step Guide</a></li>
 
