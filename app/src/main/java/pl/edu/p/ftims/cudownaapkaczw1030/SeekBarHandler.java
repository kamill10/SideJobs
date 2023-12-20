package pl.edu.p.ftims.cudownaapkaczw1030;

import android.widget.SeekBar;

public class SeekBarHandler implements SeekBar.OnSeekBarChangeListener {

    MainActivity activity;

    public SeekBarHandler(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //   tvProgress.setText( String.valueOf(progress) );
        //wartości z zakresu 200zl do 5000zl co 100
        // 200+ progress*100
       // double wart = 200+100*progress;
     //   activity.getTvProgress().setText(String.valueOf(wart));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
