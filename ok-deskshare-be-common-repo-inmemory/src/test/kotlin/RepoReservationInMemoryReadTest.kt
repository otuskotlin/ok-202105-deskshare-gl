import com.deskshare.common.repo.RepoInterface
import com.deskshare.repo.inmemory.RepoReservationInMemory
import com.deskshare.repo.test.RepoReservationReadTest

class RepoReservationInMemoryReadTest: RepoReservationReadTest() {
    override val repo: RepoInterface = RepoReservationInMemory()
}
