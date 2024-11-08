package com.alcuras.datastore.data;

import org.joda.time.DateTime;

public interface IObjectWithCreationTimestamp {

    /**
     * @param dateTime
     */
    void setCreationTimestamp(DateTime dateTime);

    /**
     * @return creation time stamp
     */
    DateTime getCreationTimestamp();

}
