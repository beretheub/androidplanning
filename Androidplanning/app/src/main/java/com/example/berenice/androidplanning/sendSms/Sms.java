package com.example.berenice.androidplanning.sendSms;

        import android.Manifest;
        import android.app.Activity;
        import android.app.PendingIntent;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.content.pm.PackageManager;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.telephony.SmsManager;
        import android.widget.Toast;

/**
 *  Envoi des SMS
 */
public class Sms {

    /**
     * Function allowing to send a text message to multiple persons at the same time
     * @param phoneNumber can be one or multiple phone numbers, divided by a comma
     * @param message the message which is going to be sent to every person
     * @param myContext
     */
    public void sendSMS(String phoneNumber, String message, final Context myContext)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(myContext, 0, new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(myContext, 0, new Intent(DELIVERED), 0);

        // Envoi du SMS
        myContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(myContext.getApplicationContext(), "SMS sent", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(myContext.getApplicationContext(), "Generic failure",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(myContext.getApplicationContext(), "No service",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(myContext.getApplicationContext(), "Null PDU",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(myContext.getApplicationContext(), "Radio off", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        // SMS recu par le destinataire ?
        myContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(myContext.getApplicationContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(myContext.getApplicationContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        String phoneNumbers[] = phoneNumber.split(",");
        if (phoneNumbers.length == 0)
        {
            Toast.makeText(myContext.getApplicationContext(), "Phone number not valid",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Demande d'envoi du SMS
        for (int i = 0; i < phoneNumbers.length; i++) {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumbers[i], null, message, sentPI, deliveredPI);
        }
    }
}