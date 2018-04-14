package omy.boston.dragdropafter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import omy.boston.dragdropafter.R;

/**
 * Created by LosFrancisco on 19-Apr-17.
 */

public class DropZone extends Fragment{
    private View dropTarget;
    private TextView dropMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dropzone, container, false);
        dropMessage= (TextView) v.findViewById(R.id.dropmessage);
        dropTarget = v.findViewById(R.id.droptarget);
        dropTarget.setOnDragListener(new View.OnDragListener() {
            private int dropCount = 0;

            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                boolean result = true;
                switch (action){
                    case DragEvent.ACTION_DRAG_STARTED:
                    case DragEvent.ACTION_DRAG_ENTERED:
                    case  DragEvent.ACTION_DRAG_EXITED:
                    case DragEvent.ACTION_DRAG_LOCATION:
                    case  DragEvent.ACTION_DRAG_ENDED:
                        break;
                    case DragEvent.ACTION_DROP:
                        String message = (++dropCount)+ "";
                        dropMessage.setText(message);
                        break;
                    default:
                        result = false;
                }
                return result;
            }
        });
        return v;
    }
}
