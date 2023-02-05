package com.example.github_worker.service.queue;

import com.example.github_worker.model.SyncRepositoryMessage;
import java.util.List;

public interface QueueService {
    List<SyncRepositoryMessage> getSyncRepositoryMessage();
}
