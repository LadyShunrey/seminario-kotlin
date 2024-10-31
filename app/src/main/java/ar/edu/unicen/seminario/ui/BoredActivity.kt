package ar.edu.unicen.seminario.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import ar.edu.unicen.seminario.databinding.ActivityBoredBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BoredActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoredBinding

    private val viewModel by viewModels<BoredViewModel>()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityBoredBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loading.onEach { loading ->
            if(loading){
                binding.progressBar.visibility = android.view.View.VISIBLE
                binding.activityInformation.visibility = android.view.View.INVISIBLE
            }else{
                binding.progressBar.visibility = android.view.View.INVISIBLE
                binding.activityInformation.visibility = android.view.View.VISIBLE
            }
        }.launchIn(lifecycleScope)

        viewModel.recommendation.onEach { recommendation ->
            binding.activityName.text = recommendation?.activity.orEmpty()
            binding.activityType.text = recommendation?.info?.type.orEmpty()
            binding.activityParticipants.text = recommendation?.info?.participants?.toString().orEmpty()
        }.launchIn(lifecycleScope)

        viewModel.error.onEach { error ->
            if(error){
                binding.error.visibility = android.view.View.VISIBLE
            }else{
                binding.error.visibility = android.view.View.INVISIBLE
            }
        }.launchIn(lifecycleScope)

        binding.participantsEditText.addTextChangedListener { text ->
            val isValid = text.toString().toIntOrNull() != null
            binding.recommendActivityButton.isEnabled = isValid
        }

        binding.recommendActivityButton.setOnClickListener {
            val participants = binding.participantsEditText.text.toString().toIntOrNull()
            if(participants != null){
                viewModel.getRecommendation(participants)
            }
        }

    }
}