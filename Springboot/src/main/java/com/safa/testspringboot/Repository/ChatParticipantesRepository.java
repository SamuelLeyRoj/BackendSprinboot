package com.safa.testspringboot.Repository;

import com.safa.testspringboot.Models.ChatParticipantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatParticipantesRepository extends JpaRepository<ChatParticipantes,Integer> {
}
