package com.truckstockapp.hvac;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;
import com.truckstockapp.hvac.MainActivity;
/**
 * Created by Dmytro on 7/20/2017.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Methods {
    Activity activity;
    String result;

    SharedPreferences History;
    SharedPreferences.Editor HistoryLine;

    String CurrentEmail;

    UserSettings User;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Methods(Activity activity, String result, int method) {
        this.activity = activity;
        this.result = result;

        User = new UserSettings(activity);

        History = activity.getSharedPreferences("HISTORY", MODE_PRIVATE);
        HistoryLine = History.edit();

        //new Message("Message", result, "OK", 1, activity);

        switch(method) {
            case 1:
                Login();
                break;
            case 2:
                Registration();
                break;
            case 3:
                RegistrationTech();
                break;
            case 4:
                CheckUpdated();
                break;
            case 5:
                Deactivate();
                break;
            case 6:
                TechniciansList();
                break;
            case 7:
                Delete();
                break;
            case 8:
                SaveSettings();
                break;
            case 9:
                ChangePassword();
                break;
            case 10:
                DeleteOwner();
                break;
            case 11:
                ListSent();
                break;
            case 12:
                GetSettings();
                break;
            case 13:
                GetHistory();
                break;
            case 14:
                DeleteHistoryLine();
                break;
            case 15:
                ResendHistoryLine();
                break;
            case 16:
                DeleteAllHistory();
                break;
            case 17:
                PutEmails();
                break;
            case 18:
                HideContacts();
                break;
            case 19:
                MessageSent();
                break;
            case 20:
                ForgotPassword();
                break;
            case 21:
                FillTechHistory();
                break;
            case 22:
                CheckHistory();
                break;
            case 23:
                CheckTrial();
                break;
            case 24:
                ChargedOwner();
                break;
            case 25:
                DeleteTrial();
                break;
            case 26:
                UpdateCard();
                break;
        }
    }

    void UpdateCard() {
        if (Objects.equals(result, "OK")) {
            RelativeLayout Nc = (RelativeLayout) activity.findViewById(R.id.NewCard);
            Nc.setVisibility(View.GONE);
            new Info("Card information updated", activity);
        } else {
            new Message("Error", result,"OK",2, activity);
        }
    }

    void DeleteTrial() {
        User.clear();
        Intent intent = new Intent(activity, Launcher.class);
        activity.startActivity(intent);
    }

    void ChargedOwner() {
        if (Objects.equals(result, "OK")) {
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
        } else {
            new Message("Error", result,"OK",2, activity);
        }

    }

    void  CheckTrial() {
        MainActivity ma = new MainActivity();

        if (Objects.equals(result, "TRIAL")) {
            User.setb("activated", true);
            ma.TrialTimer.cancel();
            Intent intent = new Intent(activity, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
        }

        if (Objects.equals(result, "END")) {
            ma.TrialTimer.cancel();
            Intent intent = new Intent(activity, EndsTrial.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
        }

    }

    void CheckHistory() {
        if (Objects.equals(result, "OK")) {
            User.setb("history", true);
        } else {
            User.setb("history", false);
        }
    }

    void FillTechHistory() {
        String[] separate = result.split(",,,");
        String Name = separate[0];
        if (!Objects.equals(separate[1], "NO")) {
            LinearLayout List = (LinearLayout) activity.findViewById(R.id.List);
            for (int c = 0; c < List.getChildCount(); c++) {
                RelativeLayout Tech = (RelativeLayout) List.getChildAt(c);
                TextView name = (TextView) Tech.findViewById(R.id.Name);
                if (Objects.equals(name.getText().toString(), Name)) {
                    Button act = (Button) Tech.findViewById(R.id.Activity);
                    ScrollView history = (ScrollView) Tech.findViewById(R.id.History);
                    act.setText("Hide activity");
                    act.setTag("hide");
                    history.setVisibility(View.VISIBLE);
                    Tech.setTag("full");

                    LinearLayout container = (LinearLayout) Tech.findViewById(R.id.Container);
                    new HistoryList(separate[1], activity, container);
                    break;
                }
            }
        } else {
            new Info("No activity with " + Name, activity);
        }
    }

    void ForgotPassword() {
        if (Objects.equals(result, "OK")) {
            new Message("Forgotten password", "We are sent your password on your registered email", "OK", 1, activity);
        }
    }

    void MessageSent() {
        if (Objects.equals(result, "OK")) {
            new Message("Message sent", "Message was successfully sent.\nThank you.", "OK", 1, activity);
        }
    }

    void PutEmails() {
        String email = result.split("~&")[2];
        if (!Objects.equals(email, "0")) {
            EditText toEmail = (EditText) activity.findViewById(R.id.ToEmail);
            String emails = toEmail.getText().toString();
            if (emails.contains(";")) {
                if(!emails.endsWith("; ")) toEmail.append("; ");
                toEmail.append(email);
                toEmail.setSelection(toEmail.getText().length());
            } else {
                toEmail.setText(email);
            }
        } else {
            new Info("NO EMAIL",activity);
        }

        HideContacts();
    }

    void HideContacts() {
        EditText message = (EditText) activity.findViewById(R.id.Message);
        ImageButton add = (ImageButton) activity.findViewById(R.id.AddEmail);
        ImageButton contact = (ImageButton) activity.findViewById(R.id.ContactsButton);

        message.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);
        contact.setImageResource(R.drawable.contacts);
    }

    void DeleteAllHistory() {
        HistoryLine.clear().apply();
        activity.finish();
    }

    void ResendHistoryLine() {
        if (Objects.equals(result, "OK")) {
            new Message("List resent", "Your order list was successfull resent.\nThank you","OK",1,activity);
        }
    }

    void DeleteHistoryLine() {

        activity.recreate();
    }

    void GetHistory() {
        String[] separate = result.split(",,,");
        if (Objects.equals(separate[1], "NO")) {
            User.setb("history", false);
            //new Message("No History", "Nothing to show.\nMake a first Order List.\nThank you.", "OK", 2, activity);
            activity.finish();
        } else {
            User.setb("history", true);
            LinearLayout container = (LinearLayout) activity.findViewById(R.id.HistoryList);
            new HistoryList(separate[1], activity, container);
        }
    }

    void GetSettings() {

        String[] Lines = result.split("~~");

        EditText name = (EditText) activity.findViewById(R.id.Name);
        EditText company = (EditText) activity.findViewById(R.id.Company);
        EditText address = (EditText) activity.findViewById(R.id.Address);
        EditText city = (EditText) activity.findViewById(R.id.City);
        EditText state = (EditText) activity.findViewById(R.id.State);
        EditText zip = (EditText) activity.findViewById(R.id.Zip);
        EditText phone = (EditText) activity.findViewById(R.id.Phone);
        EditText recipient = (EditText) activity.findViewById(R.id.Recipient);

        name.setText(Lines[6]); User.set("name", Lines[6]);
        company.setText(Lines[0]); User.set("company", Lines[0]);
        address.setText(Lines[1]); User.set("address", Lines[1]);
        city.setText(Lines[2]); User.set("city", Lines[2]);
        state.setText(Lines[3]); User.set("state", Lines[3]);
        zip.setText(Lines[4]); User.set("zip", Lines[4]);
        phone.setText(Lines[5]); User.set("phone", Lines[5]);
        recipient.setText(Lines[7]); User.set("recipient", Lines[7]);
    }

    void ListSent() {
        if (Objects.equals(result, "OK")) {
            new Message("List sent", "Your order list was successfull sent.\nYou can reach it in the History section for viewing or resending.\nThank you","OK",1,activity);
        }
    }

    void DeleteOwner() {
        //new Info(result, activity);

        if (Objects.equals(result, "OK")) {
            User.clear();
            Intent intent = new Intent(activity, Launcher.class);
            activity.startActivity(intent);
        } else {
            new Message("Error", "Can't unsubscribe this time.\nPlease try later.", "OK", 2, activity);
        }
    }

    void ChangePassword() {
        if (!Objects.equals(result, "NO")) {
            User.set("password", result);
            new Message("Success", "Your password has been changed successfully", "OK", 1, activity);
        } else {
            new Message("Can't save", "Can't save new password.\nPlease try later.\nThank you.", "OK", 2, activity);
        }
    }

    void SaveSettings() {

        if (Objects.equals(result, "OK")) {

            EditText name = (EditText) activity.findViewById(R.id.Name);
            EditText company = (EditText) activity.findViewById(R.id.Company);
            EditText address = (EditText) activity.findViewById(R.id.Address);
            EditText city = (EditText) activity.findViewById(R.id.City);
            EditText state = (EditText) activity.findViewById(R.id.State);
            EditText zip = (EditText) activity.findViewById(R.id.Zip);
            EditText phone = (EditText) activity.findViewById(R.id.Phone);
            EditText recipient = (EditText) activity.findViewById(R.id.Recipient);

            User.set("name", name.getText().toString());
            User.set("company", company.getText().toString());
            User.set("address", address.getText().toString());
            User.set("phone", phone.getText().toString());
            User.set("city", city.getText().toString());
            User.set("state", state.getText().toString());
            User.set("zip", zip.getText().toString());
            User.set("phone", phone.getText().toString());
            User.set("recipient", recipient.getText().toString());

            activity.finish();
        } else {
            new Message("Can't save", "Can't save a settings.\nPlease try later.\nThank you.", "OK", 1, activity);
        }

    }

    void TechniciansList() {

        String[] s = result.split("~~");
        LinearLayout List;
        TextView Name;
        LayoutInflater inflater;
        View Tech;
        switch (s[0]) {
            case "OK":
                String[] Data = s[1].split("&&");

                List = (LinearLayout)activity.findViewById(R.id.List);

                final String[] colors = new String[] {
                        "#FFFBB040", "#FF1C75BC","#FFEC008C","#FFC2B59B","#FF00A651","#FF754C29","#241f7a"
                };
                int col = 0;

                for (int i = 0; i < Data.length; i++) {

                    inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    Tech = inflater.inflate(R.layout.technician_line, null);
                    Name = (TextView) Tech.findViewById(R.id.Name);

                    TextView Email = (TextView) Tech.findViewById(R.id.Email);
                    //TextView Address = (TextView) Tech.findViewById(R.id.Address);
                    //TextView City = (TextView) Tech.findViewById(R.id.City);
                    TextView Phone = (TextView) Tech.findViewById(R.id.Phone);
                    TextView Date = (TextView) Tech.findViewById(R.id.Date);
                    Button activ = (Button) Tech.findViewById(R.id.Activity);
                    Button delete = (Button) Tech.findViewById(R.id.Delete);

                    activ.setTag("view");

                    String[] technician = Data[i].split("##");
                    Name.setText(technician[0]);
                    Email.setText(technician[1]);
                    Phone.setText(technician[4]);
                    Date.setText(technician[5]);

                    RelativeLayout Line = (RelativeLayout)Tech.findViewById(R.id.Title);
                    Line.setBackgroundColor(Color.parseColor(colors[col])); col ++; if (col>6) col = 0;

                    Tech.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ImageView galC = (ImageView) view.findViewById(R.id.Close);
                            ImageView galO = (ImageView) view.findViewById(R.id.Open);
                            RelativeLayout Details = (RelativeLayout) view.findViewById(R.id.Detalies);
                            if (galC.getVisibility() == View.VISIBLE) {
                                galC.setVisibility(View.GONE);
                                galO.setVisibility(View.VISIBLE);
                                Details.setVisibility(View.VISIBLE);
                            } else {
                                galC.setVisibility(View.VISIBLE);
                                galO.setVisibility(View.GONE);
                                Details.setVisibility(View.GONE);
                            }
                        }
                    });

                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RelativeLayout det = (RelativeLayout) view.getParent();
                            TextView email = (TextView) det.findViewById(R.id.Email);
                            CurrentEmail = email.getText().toString();
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setTitle("ARE YOU SURE?");
                            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    HashMap<String, String> Post = new HashMap<>();
                                    Post.put("email", CurrentEmail);
                                    Post.put("pid", User.get("pid"));
                                    new ServerScript(Post, "http://truckstockapp.com/plans/scripts/deletetechnician.php", activity, 7).execute();
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { public void onClick(DialogInterface dialog, int id) { } });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

                    activ.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Button act = (Button) view;
                            RelativeLayout Tech = (RelativeLayout) act.getParent();
                            RelativeLayout Lay = (RelativeLayout) Tech.getParent();
                            if (Lay.getTag() != "full") {
                                HashMap<String, String> Post = new HashMap<>();
                                TextView Email = (TextView)Tech.findViewById(R.id.Email);
                                String email = Email.getText().toString();
                                Post.put("email", email);
                                new ServerScript(Post, "http://truckstockapp.com/plans/scripts/gethistory2.php", activity, 21).execute();
                            } else {
                                ScrollView history = (ScrollView) Tech.findViewById(R.id.History);
                                if (act.getTag() == "view") {
                                    act.setText("Hide activity");
                                    act.setTag("hide");
                                    history.setVisibility(View.VISIBLE);
                                } else {
                                    act.setText("View activity");
                                    act.setTag("view");
                                    history.setVisibility(View.GONE);
                                }
                            }
                        }
                    });

                    List.addView(Tech);
                }

                break;
            case "NO":
                List = (LinearLayout)activity.findViewById(R.id.List);
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                Tech = inflater.inflate(R.layout.technician_line, null);
                Name = (TextView) Tech.findViewById(R.id.Name);
                Name.setText("NO TECHNICIANS");;
                List.addView(Tech);
                break;
        }
    }

    void Delete() {
        if (!Objects.equals(result, "NO")) {
            new Message("Deleted", "Your technician " + result + " has been deleted", "OK", 1, activity);
            activity.recreate();
        }
    }

    void CheckUpdated() {
        if (!Objects.equals(result, "NO")) {

            String[] got = result.split("%%");
            User.set("lastupdated", got[0]);
            User.set("list", got[1]);

            new Message("Updated.!", "Your items list was updated.\n" +
                    "Enjoy it and Thank you.", "GOT IT", 1, activity);

        }
    }

    void Login() {
        String[] s = result.split("/");
        switch (s[0]) {
            case "OK":
                //if (Objects.equals(s[4], "1")) {
                    //User.clear();
                    CheckBox autologin = (CheckBox) activity.findViewById(R.id.Autologin);
                    if (autologin.isChecked()) {
                        User.setb("autologin", true);
                    } else {
                        User.setb("autologin", false);
                    }
                    EditText Email = (EditText) activity.findViewById(R.id.Email);
                    EditText Password = (EditText) activity.findViewById(R.id.PasswordT);

                    User.set("name", s[2]);
                    User.set("email", Email.getText().toString());
                    User.set("password", Password.getText().toString());
                    if (Objects.equals(s[1], "1")) {
                        User.setb("owner", true);
                    } else {
                        User.setb("owner", false);
                        User.set("oid", s[3]);
                        User.setb("activated", Objects.equals(s[4], "1")? true : false);
                        User.set("owneremail", s[13]);
                        User.set("ownerphone", s[14]);
                    }

                    if (Objects.equals(s[4], "1")) {
                        User.setb("activated", true);
                    } else {
                        User.setb("activated", false);
                    }

                    User.set("company", s[6]);
                    User.set("address", s[7]);
                    User.set("city", s[8]);
                    User.set("state", s[9]);
                    User.set("zip", s[10]);
                    User.set("phone", s[11]);
                    User.set("recipient", s[12]);

                    if (!User.is("list")) {
                        try {
                            InputStream in_s = activity.getResources().openRawResource(R.raw.list);
                            byte[] b = new byte[in_s.available()];
                            in_s.read(b);
                            User.set("list", new String(b));
                        } catch (Exception e) {
                        }

                    }

                    //new Info(USER.getString("owneremail", "NO"), activity);

                    Intent intent = new Intent(activity, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    activity.startActivity(intent);
                /*} else {
                    new Message("Deactivated", "Your owner of application has deactivated your account.\n" +
                            "Please take a connect with him.\n" +
                            "Thank you.", "OK", 2, activity);
                }*/
                break;

            case "PASS":
                new Message("Wrong Password", "Please enter the correct password\n" +
                        "Thank you.", "OK", 2, activity);
                break;

            case "LOG":
                new Message("No user", "User not registred.\n" +
                        "Please register as owner.\n" +
                        "If you are team technician then owner of this app\n" +
                        "have to register you from admin screen.\n" +
                        "Thank you.", "OK", 2, activity);
                break;
        }

    }

    void Registration() {
        switch (result) {
            case "1":
                new Message("Problem with charging", "We can't charge your credit card\n" +
                        "Please check card information.\n" +
                        "Thank you.", "OK", 2, activity);
                break;
            case "2":
                new Message("Wrong e-mail", "User with this e-mail is already registred", "OK", 2, activity);
                break;
            case "OK":
                new Message("Congrats!", "You are registered!\nYou can login now.", "OK", 1, activity);
                EditText Name = (EditText) activity.findViewById(R.id.Name);
                EditText Email = (EditText) activity.findViewById(R.id.Email);
                EditText Password = (EditText) activity.findViewById(R.id.Password1);
                EditText Phone = (EditText) activity.findViewById(R.id.Phone);
                EditText Address = (EditText) activity.findViewById(R.id.Address);
                EditText City = (EditText) activity.findViewById(R.id.City);
                EditText State = (EditText) activity.findViewById(R.id.State);
                EditText Zip = (EditText) activity.findViewById(R.id.Zip);
                User.clear();
                User.set("name", Name.getText().toString());
                User.set("email", Email.getText().toString());
                User.set("password", Password.getText().toString());
                User.set("phone", Phone.getText().toString());
                User.set("address", Address.getText().toString());
                User.set("city", City.getText().toString());
                User.set("state", State.getText().toString());
                User.set("zip", Zip.getText().toString());
                User.setb("owner", true);
                User.setb("activated", false);

                Intent intent = new Intent(activity, Launcher.class);
                activity.startActivity(intent);
                break;
        }
    }

    void RegistrationTech() {
        switch (result) {
            case "1":
                new Message("Problem with charging", "We can't charge your credit card\n" +
                        "Please check card information.\n" +
                        "Thank you.", "OK", 2, activity);
                break;
            case "2":
                new Message("Wrong e-mail", "User with this e-mail is already registered", "OK", 2, activity);
                break;
            case "OK":
                LinearLayout NewForm = (LinearLayout) activity.findViewById(R.id.NewTechForm);
                TextView NameF = (TextView) NewForm.findViewById(R.id.Name);
                TextView EmailF = (TextView) NewForm.findViewById(R.id.Email);
                TextView PassF = (TextView) NewForm.findViewById(R.id.PasswordT);
                //TextView PhoneF = (TextView) NewForm.findViewById(R.id.Phone);
                ImageView Gal = (ImageView) activity.findViewById(R.id.Closed);
                ImageView Gal2 = (ImageView) activity.findViewById(R.id.Opend);
                Gal.setVisibility(View.VISIBLE);
                Gal2.setVisibility(View.GONE);
                NameF.setText(""); EmailF.setText(""); PassF.setText(""); //PhoneF.setText("");
                NewForm.setVisibility(View.GONE);
                activity.recreate();
                new Message("Congrats!", "New technician "+NameF.getText().toString()+" was added!\n" +
                        "We are sent invitation him on "+ EmailF.getText().toString()+"\ne-mail.\n" +
                        "Thank you.", "OK", 1, activity);
                break;
        }

    }

    void Deactivate() {
        String[] s = result.split("/");
        if (!Objects.equals(s[4], "1")) {
            User.setb("autologin", false);
            Intent intent = new Intent(activity, Launcher.class);
            activity.startActivity(intent);
        }
    }

}
