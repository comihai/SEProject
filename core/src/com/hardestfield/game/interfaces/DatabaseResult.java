package com.hardestfield.game.interfaces;

/**
 * Created by mihai on 1/24/2015.
 * THis interface will be implemented on both Android and Desktop applications
 */
public interface DatabaseResult {
    public boolean isEmpty();

    public boolean moveToNext();

    public int getColumnIndex(String name);

    public float getFloat(int columnIndex);
}
