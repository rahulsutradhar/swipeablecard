package get.basis.swipecarddemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.top_content)
    ConstraintLayout topContent;
    @BindView(R.id.bottom_panel)
    ConstraintLayout bottomPanel;
    @BindView(R.id.cardstackview)
    CardStackView cardStackView;
    @BindView(R.id.reset)
    AppCompatButton reset;
    @BindView(R.id.counter_text)
    AppCompatTextView counterTextView;
    @BindView(R.id.rewind)
    AppCompatButton rewind;

    private CardStackAdapter adapter;
    private CardStackLayoutManager manager;

    private ArrayList<CardModel> cardModels = new ArrayList<>();

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupData();
        setupViews();

    }

    /**
     * Setting up data
     */
    private void setupData() {
        for ( int i = 1; i <= 5; i++ ) {
            CardModel cardModel = new CardModel();
            cardModel.setCount(i);
            cardModel.setContent("This is card Number - " + i);
            cardModels.add(cardModel);
        }
    }

    private void setupViews() {
        manager = new CardStackLayoutManager(this, cardStackListener);
        adapter = new CardStackAdapter(this, cardModels);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);

        //decorations
        manager.setStackFrom(StackFrom.Bottom);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(4f);
        manager.setScaleInterval(0.95f);
        manager.setMaxDegree(20f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setSwipeThreshold(0.3f);

        /**
         * Swipe from Bottom, then it will rewind
         */
        bottomPanel.setOnTouchListener(new OnSwipeTouchListener(this) {

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                if ( manager != null ) {
                    cardStackView.rewind();
                }
            }
        });
    }

    private CardStackListener cardStackListener = new CardStackListener() {
        @Override
        public void onCardDragging( Direction direction, float ratio ) {

        }

        @Override
        public void onCardSwiped( Direction direction ) {

        }

        @Override
        public void onCardRewound() {
        }

        @Override
        public void onCardCanceled() {

        }

        @Override
        public void onCardAppeared( View view, int position ) {
            updateCounter(position);
        }

        @Override
        public void onCardDisappeared( View view, int position ) {
            updateCounter(position);
        }
    };

    @OnClick({ R.id.reset, R.id.rewind })
    public void onViewClicked( View view ) {
        switch ( view.getId() ) {
            case R.id.reset:
                if ( adapter != null ) {
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.rewind:
                if ( manager != null ) {
                    cardStackView.rewind();
                }
                break;
        }
    }

    private void updateCounter( int topPosition ) {
        if ( topPosition >= cardModels.size() ) {
            counterTextView.setText(String.valueOf(0));
        } else {
            counterTextView.setText(String.valueOf(topPosition + 1));
        }
    }
}
