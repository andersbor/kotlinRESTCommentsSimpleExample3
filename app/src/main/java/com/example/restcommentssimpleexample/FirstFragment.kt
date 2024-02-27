package com.example.restcommentssimpleexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restcommentssimpleexample.databinding.FragmentFirstBinding
import com.example.restcommentssimpleexample.models.Comment
import com.example.restcommentssimpleexample.models.CommentsViewModel

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val commentsViewModel: CommentsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commentsViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            binding.textviewMessage.text = errorMessage
        }

        commentsViewModel.commentsLiveData.observe(viewLifecycleOwner) { comments ->
            if (comments == null) {
                binding.textviewMessage.text = "No data, strange ..."
            } else {
                val adapter = MyAdapter(comments) { position ->
                    val comment: Comment? = commentsViewModel.get(position)
                    if (comment == null) {
                        binding.textviewMessage.text = "No data, strange ..."
                        return@MyAdapter
                    }
                    val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(comment)
                    findNavController().navigate(action)
                }
                binding.recyclerview.layoutManager = LinearLayoutManager(activity)
                binding.recyclerview.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}