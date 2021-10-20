import com.deskshare.common.repo.RepoInterface
import com.deskshare.repo.inmemory.RepoReservationInMemory
import com.deskshare.repo.test.RepoReservationUpdateTest

class RepoReservationInMemoryUpdateTest: RepoReservationUpdateTest() {
    override val repo: RepoInterface = RepoReservationInMemory()
}
