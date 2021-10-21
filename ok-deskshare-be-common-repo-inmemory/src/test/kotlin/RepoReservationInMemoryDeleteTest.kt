import com.deskshare.common.repo.RepoInterface
import com.deskshare.repo.inmemory.RepoReservationInMemory
import com.deskshare.repo.test.RepoReservationDeleteTest
import com.deskshare.repo.test.RepoReservationUpdateTest

class RepoReservationInMemoryDeleteTest: RepoReservationDeleteTest() {
    override val repo: RepoInterface = RepoReservationInMemory()
}
