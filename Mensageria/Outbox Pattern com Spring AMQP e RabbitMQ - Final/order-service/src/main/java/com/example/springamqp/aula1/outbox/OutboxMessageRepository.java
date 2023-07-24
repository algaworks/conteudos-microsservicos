package com.example.springamqp.aula1.outbox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface OutboxMessageRepository extends JpaRepository<OutboxMessage, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<OutboxMessage> findFirst10ByStatusOrderByCreatedAtAsc(OutboxMessage.Status status);

}
