package com.kariaki.choice.ui.dialogbox;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.kariaki.choice.R;
import com.kariaki.choice.ui.util.Theme;

import static android.content.Context.MODE_PRIVATE;

public class ChoiceNewDialogBox extends DialogFragment {


    @Override
    public void setStyle(int style, int theme) {

        super.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Theme_Dialog);
    }

    TextView choiceDialogTittle,choiceDialogMessage,dialogCancelProcess
            ,choiceDialogConfirmProcess;
    private String tittle,message;
    RelativeLayout rootView;

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public interface dialogButtons{
        void onClickPositiveButton();

        void onClickNegativeButton();
    }

    private ChoiceDialogBox.dialogButtons listeners;

    public void setListeners(ChoiceDialogBox.dialogButtons listeners) {
        this.listeners = listeners;
    }

    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        View contentView=View.inflate(getContext(), R.layout.choice_dialog_box,null);
        dialog.setContentView(contentView);
        ( (View)contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));

        rootView=contentView.findViewById(R.id.rootView);
        choiceDialogTittle=contentView.findViewById(R.id.choiceDialogTittle);
        choiceDialogMessage=contentView.findViewById(R.id.choiceDialogMessage);
        dialogCancelProcess=contentView.findViewById(R.id.dialogCancelProcess);
        choiceDialogConfirmProcess=contentView.findViewById(R.id.choiceDialogConfirmProcess);
        loadTheme();
        choiceDialogTittle.setText(tittle);
        choiceDialogMessage.setText(message);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        dialogCancelProcess.setOnClickListener(v->{
            listeners.onClickNegativeButton();
        });
        choiceDialogConfirmProcess.setOnClickListener(v->{
            listeners.onClickPositiveButton();
        });
    }



    public void setTextColors( int currentTheme){
        switch (currentTheme){
            case Theme.LIGHT:
                choiceDialogMessage.setTextColor(getResources().getColor(R.color.textColor));
              //  rootView.setBackgroundColor(getResources().getColor(R.color.whiteGreen));
                rootView.setBackground(getResources().getDrawable(R.drawable.full_curve));

                break;
            case Theme.DEEP:

                rootView.setBackground(getResources().getDrawable(R.drawable.full_curve_dark));
                choiceDialogMessage.setTextColor(getResources().getColor(R.color.whiteGreen));
                break;

        }
    }
    private void loadTheme(){
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("themes",MODE_PRIVATE);

        int currentTheme=sharedPreferences.getInt("currentTheme", Theme.DEEP);

        setTextColors(currentTheme);


    }

}
