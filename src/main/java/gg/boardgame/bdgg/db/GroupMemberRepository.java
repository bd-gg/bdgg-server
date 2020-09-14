package gg.boardgame.bdgg.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    Optional<GroupMember> findByUser_IdAndGroup_Id(long userId, long groupId);
    Optional<List<GroupMember>> findByUser_id(long userId);
    Optional<List<GroupMember>> findByGroup_Id(long groupId);
}
