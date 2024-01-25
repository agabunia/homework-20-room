package com.example.homework_20_rooms.presentation.screen.users

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework_20_rooms.databinding.FragmentUsersBinding
import com.example.homework_20_rooms.presentation.base.BaseFragment
import com.example.homework_20_rooms.presentation.event.UsersEvent
import com.example.homework_20_rooms.presentation.state.users.UserState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {
    private val viewModel: UsersViewModel by viewModels()

    override fun bind() {}

    override fun bindListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.onEvent(
                UsersEvent.CreateUser(
                    firstName = binding.etFirstName.text.toString(),
                    lastName = binding.etLastName.text.toString(),
                    age = binding.etAge.text.toString(),
                    email = binding.etEmail.text.toString()
                )
            )
            clearFields()
        }

        binding.btnRemove.setOnClickListener {
            viewModel.onEvent(
                UsersEvent.DeleteUser(
                    firstName = binding.etFirstName.text.toString(),
                    lastName = binding.etLastName.text.toString(),
                    age = binding.etAge.text.toString(),
                    email = binding.etEmail.text.toString()
                )
            )
            clearFields()
        }

        binding.btnUpdate.setOnClickListener {
            viewModel.onEvent(
                UsersEvent.UpdateUser(
                    firstName = binding.etFirstName.text.toString(),
                    lastName = binding.etLastName.text.toString(),
                    age = binding.etAge.text.toString(),
                    email = binding.etEmail.text.toString()
                )
            )
            clearFields()
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userState.collect {
                    handleState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }
    }

    private fun clearFields() {
        binding.apply {
            etFirstName.text?.clear()
            etLastName.text?.clear()
            etAge.text?.clear()
            etEmail.text?.clear()
        }
    }

    private fun handleState(state: UserState) {
        state.userMessage?.let {
            toastMessage(it)
            viewModel.onEvent(UsersEvent.ResetMessage)
        }

    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}
