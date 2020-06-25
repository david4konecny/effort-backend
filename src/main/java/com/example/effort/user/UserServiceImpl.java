package com.example.effort.user;

import com.example.effort.category.CategoryRepository;
import com.example.effort.review.ReviewRepository;
import com.example.effort.task.TaskRepository;
import com.example.effort.time.TimeRepository;
import com.example.effort.util.SampleData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;
    private final TimeRepository timeRepository;
    private final SampleData sampleData;

    public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository, CategoryRepository categoryRepository, ReviewRepository reviewRepository, TimeRepository timeRepository, SampleData sampleData) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
        this.reviewRepository = reviewRepository;
        this.timeRepository = timeRepository;
        this.sampleData = sampleData;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public void addSampleData(User user) {
        sampleData.addSampleData(user);
    }

    @Override
    public void editUsername(String newUsername) {
        userRepository.editUsername(newUsername);
    }

    @Transactional
    @Override
    public void deleteUser() {
        timeRepository.deleteEntries();
        taskRepository.deleteTasks();
        reviewRepository.deleteReviews();
        categoryRepository.deleteCategories();
        userRepository.deleteUser();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
