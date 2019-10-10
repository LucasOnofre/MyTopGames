package onoffrice.mytopgames.ui.topgames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import onoffrice.mytopgames.R
import onoffrice.mytopgames.data.remote.TopGamesContract

class TopGamesActivity : AppCompatActivity(), TopGamesContract.View{

    private val presenter: TopGamesContract.Presenter by lazy {
        TopGamesPresenter().apply {
            attachView(this@TopGamesActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_games)
    }

    override fun displayLoading(loading: Boolean) {

    }

    override fun displayError(message: String?) {

    }
}
