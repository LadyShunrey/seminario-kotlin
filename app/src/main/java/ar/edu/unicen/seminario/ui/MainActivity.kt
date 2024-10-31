package ar.edu.unicen.seminario.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ar.edu.unicen.seminario.R
import ar.edu.unicen.seminario.databinding.ActivityMainBinding
import ar.edu.unicen.seminario.ddl.CounterLocalDataSource
import ar.edu.unicen.seminario.ddl.CounterRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()
//        factoryProducer = {
//           viewModelFactory {
//               initializer {
//                   val sharedPreferences = getSharedPreferences("counter", Context.MODE_PRIVATE)
//                   val localDataSource = CounterLocalDataSource(sharedPreferences)
//                   val repository = CounterRepository(localDataSource)
//                   MainViewModel(repository)
//               }
//           }
//        }
//    )

//    @Inject
//    lateinit var viewModel: MainViewModel

//    private val viewModel by viewModels<MainViewModel>()

//    private var counter: Counter = Counter()

//    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val button: Button = findViewById(R.id.click_me_button)

        binding.clickMeButton.setOnClickListener {
            viewModel.increment()
//            counter.incrementar()
//            counter++
//            binding.helloWorldText.text = counter.toString()
        }

//        viewModel.counter.observe(this) { valor ->
//            binding.counterText.text = valor.toString()
//        }

        binding.resetButton.setOnClickListener {
            viewModel.reset()
        }

        viewModel.counter.onEach { valor ->
            binding.counterText.text = valor.toString()
        }.launchIn(lifecycleScope)

        viewModel.loading.onEach { loading ->
            if(loading){
                binding.counterText.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.VISIBLE
                binding.clickMeButton.isEnabled = false
                binding.navigateButton.isEnabled = false
            } else {
                binding.progressBar.visibility = View.INVISIBLE
                binding.counterText.visibility = View.VISIBLE
                binding.clickMeButton.isEnabled = true
                binding.navigateButton.isEnabled = true
            }
        }.launchIn(lifecycleScope)

//        viewModel.loading.observe(this) { loading ->
//            if(loading){
//                binding.counterText.visibility = View.INVISIBLE
//                binding.progressBar.visibility = View.VISIBLE
//                binding.clickMeButton.isEnabled = false
//                binding.navigateButton.isEnabled = false
//            } else {
//                binding.progressBar.visibility = View.INVISIBLE
//                binding.counterText.visibility = View.VISIBLE
//                binding.clickMeButton.isEnabled = true
//                binding.navigateButton.isEnabled = true
//            }
//        }

        binding.navigateButton.setOnClickListener {
            val intent = Intent(this, RedActivity::class.java)

            intent.putExtra("counter", viewModel.counter.value)

//            viewModel.counter.observe(this) { valor ->
//                binding.counterText.text = valor.toString()
//                intent.putExtra("counter", valor)
//            }

//            intent.putExtra("counter", counter)

            startActivity(intent)
        }

        binding.boredButton.setOnClickListener {
            val intent = Intent(this, BoredActivity::class.java)

            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.i("MainActivity","onNewIntent")

        val toAdd = intent?.getIntExtra("add_counter", 0)
        if (toAdd != null) {
//            counter += toAdd
//            binding.counterText.text = counter.toString()
        }
    }

//    override fun onSaveInstanceState(outState: Bundle){
//        super.onSaveInstanceState(outState)
//        Log.i("MainActivity","onSaveInstanceState")
////        outState.putParcelable("counter", counter)
//        outState.putInt("counter", counter)
//    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle){
//        super.onRestoreInstanceState(savedInstanceState)
//        Log.i("MainActivity","onRestoreInstanceState")
////        val savedCounter = savedInstanceState.getParcelable<Counter>("counter")
//        val savedCounter = savedInstanceState.getInt("counter")
//        if (savedCounter != null) {
//            counter = savedCounter
//
//            binding.counterText.text = counter.toString()
//        }
//    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart")
    }

    override fun onResume(){
        super.onResume()
        Log.i("MainActivity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity","onPause")
    }

    override fun onStop(){
        super.onStop()
        Log.i("MainActivity","onStop")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i("MainActivity","onDestroy")
    }

}

//
