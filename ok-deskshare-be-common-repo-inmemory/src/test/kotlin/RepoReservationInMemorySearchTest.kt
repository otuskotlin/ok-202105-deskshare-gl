import com.deskshare.common.repo.RepoInterface
import com.deskshare.repo.inmemory.RepoReservationInMemory
import com.deskshare.repo.test.RepoReservationReadTest
import com.deskshare.repo.test.RepoReservationSearchTest

class RepoReservationInMemorySearchTest: RepoReservationSearchTest() {
    override val repo: RepoInterface = RepoReservationInMemory()
}
