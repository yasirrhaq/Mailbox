package com.example.papbmailbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMail;
    private MailAdapter mailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMail = findViewById(R.id.rvMail);
        rvMail.setLayoutManager(new LinearLayoutManager(this));

        List<Mail> mails = new ArrayList<>();
        putData(mails);

        mailAdapter = new MailAdapter(this, mails);
        rvMail.setAdapter(mailAdapter);

        EditText etFilter = this.findViewById(R.id.editTextFilter);
        setupFilter(etFilter);
    }

    private void setupFilter(EditText etFilter){
        etFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mailAdapter.getFilter().filter(editable.toString());
            }
        });
    }

    private void putData(List<Mail> mails) {
        String[] senders = {"Bugon", "Bukan Bugon", "Brando", "Windah Batubara", "Fitriani", "Budi Santoso", "Linda Wijaya", "Andi Maulana", "Siska Putri", "Fajar Wicaksono", "Nadia Permatasari", "Dwi Cahyono", "Lia Pratiwi", "Rio Nugroho", "Yuni Triana"};
        String[] messages = {"Halo teman-teman", "Halo adik-adik", "Saya izin tidak masuk dulu ya", "Saya top up 40 juta demi haaland terkuat", "Hari ini cuaca sangat cerah", "Ayo kita makan siang bersama", "Tolong kirimkan laporan yang diperlukan", "Terima kasih atas bantuannya", "Jangan lupa datang ke rapat besok ya", "Sudahkah kamu membaca buku baru itu?", "Saya akan cuti minggu depan", "Selamat ulang tahun ya!", "Saya sedang dalam perjalanan ke kantor", "Tolong berikan saya kabar terbaru tentang proyek itu", "Apa kabar, semuanya?"};

        for (int i = 0; i < senders.length; i++) {
            Mail mail = new Mail(senders[i], messages[i]);
            mails.add(mail);
        }
    }
}