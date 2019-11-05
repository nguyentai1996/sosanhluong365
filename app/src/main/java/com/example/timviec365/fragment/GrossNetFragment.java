package com.example.timviec365.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.timviec365.R;

import java.text.NumberFormat;
import java.util.Locale;

import static com.example.timviec365.activity.DetailSalaryComparison.hideSoftKeyboard;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrossNetFragment extends Fragment {
    private EditText edSalary;
    private LinearLayout imgConectApp;
    private EditText edDependent;
    private EditText edInsurrance;
    private RadioButton btnOther;
    private RadioButton btnSalary;
    private Button btnNetGross;
    private Button btnGrossNet;
    private RadioButton btnOne;
    private RadioButton btnTwo;
    private RadioButton btnThree;
    private RadioButton btnFour;
    private TextView tvGrossSalary, TTNCN, TNCT, TNTT, tvIncomeTax, TaxTNCN5, TaxTNCN10, TaxTNCN15, TaxTNCN20, TaxTNCN25, TaxTNCN30, TaxTNCN35, tvTax, tvInsurrance, tvBHXH_NTD, tvBHYT_NTD, tvBHTN_NTD, tvBHTNLD_NTD;
    private TextView tvNetSalary, tvGross, tvFamily, tvSocialInsurance, tvHealthInsurance, tvNet, tvUnemployment;
    private String salaryGrossNet = "", other = "", dependent = "";
    long Total = 0;
    long lct = 0;
    long TotalTaxInsurance = 0;
    long tncn = 0;
    long tnct = 0;
    long tncn1 = 0;
    long tncn2 = 0;
    long tncn3;
    long IncomeTax = 0, TotalInsurance = 0;
    long Net = 0;
    long tncn4;

    long tncn5;
    long tncn6;
    long tncn7;
    long TaxThunhaptruocthue = 0;
    long TaxThunhapcanhan = 0;
    long TaxBHXH = 0;
    long TaxBHYT = 0;
    long TaxBHTN = 0;
    long TaxBHGC = 0;

    long in = 0;
    long z = 0;
    long dependentt = 0;
    long otherr = 0;

    long bhxh = 0;
    long bhyt = 0;
    long bhtn = 0;
    long bhxh_ntd = 0;
    long bhtnld = 0;
    long bhyt_ntd = 0;
    long bhtn_ntd = 0;

    private Boolean chooser = true;


    public GrossNetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grossnet, container, false);
        setupUI(view.findViewById(R.id.parent));


        edSalary = view.findViewById(R.id.edSalary);
        imgConectApp = view.findViewById(R.id.imgConectApp);
        edInsurrance = view.findViewById(R.id.other);

        edDependent = view.findViewById(R.id.edDependent);
        TNCT = view.findViewById(R.id.TNCT);
        TNTT = view.findViewById(R.id.TNTT);
        TTNCN = view.findViewById(R.id.TTNCN);
        tvFamily = view.findViewById(R.id.tvFamily);
        tvInsurrance = view.findViewById(R.id.tvInsurrance);
        tvSocialInsurance = view.findViewById(R.id.tvSocialInsurance);
        tvHealthInsurance = view.findViewById(R.id.tvHealthInsurance);
        tvNet = view.findViewById(R.id.tvNet);
        tvGross = view.findViewById(R.id.tvGross);
        tvTax = view.findViewById(R.id.tvTax);
        tvUnemployment = view.findViewById(R.id.tvUnemployment);
        btnOther = view.findViewById(R.id.btnOther);
        btnSalary = view.findViewById(R.id.btnSlary);
        btnGrossNet = view.findViewById(R.id.btnGrossNet);
        btnNetGross = view.findViewById(R.id.btnNetGross);
        tvGrossSalary = view.findViewById(R.id.tvGrossSalary);
        tvBHTN_NTD = view.findViewById(R.id.tvBHTN_NTD);
        tvBHTNLD_NTD = view.findViewById(R.id.tvBHTNLD_NTD);
        tvBHXH_NTD = view.findViewById(R.id.tvBHXH_NTD);
        tvBHYT_NTD = view.findViewById(R.id.tvBHYT_NTD);
        tvNetSalary = view.findViewById(R.id.tvNetSalary);
        btnOne = view.findViewById(R.id.btnOne);
        btnTwo = view.findViewById(R.id.btnTwo);
        btnThree = view.findViewById(R.id.btnThree);
        btnFour = view.findViewById(R.id.btnFour);
        tvIncomeTax = view.findViewById(R.id.tvIncomeTax);

        TaxTNCN5 = view.findViewById(R.id.TaxTNCN5);
        TaxTNCN10 = view.findViewById(R.id.TaxTNCN10);
        TaxTNCN15 = view.findViewById(R.id.TaxTNCN15);
        TaxTNCN20 = view.findViewById(R.id.TaxTNCN20);
        TaxTNCN25 = view.findViewById(R.id.TaxTNCN25);
        TaxTNCN35 = view.findViewById(R.id.TaxTNCN35);
        TaxTNCN30 = view.findViewById(R.id.TaxTNCN30);

        imgConectApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getContext().getPackageManager().getLaunchIntentForPackage("vn.timviec365.myapplication");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });

        btnGrossNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnGrossNet.setBackgroundColor(getResources().getColor(R.color.yellow));
                btnGrossNet.setTextColor(getResources().getColor(R.color.white));
                btnNetGross.setTextColor(getResources().getColor(R.color.yellow));
                btnNetGross.setBackgroundColor(getResources().getColor(R.color.white));


                salaryGrossNet = edSalary.getText().toString();
                other = edInsurrance.getText().toString();
                dependent = edDependent.getText().toString();


                if (salaryGrossNet.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập thu nhập của bạn", Toast.LENGTH_SHORT).show();
                } else if (dependent.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập số người phụ thuộc", Toast.LENGTH_SHORT).show();
                } else {

                    if (btnSalary.isChecked()) {

                        Locale localeEN = new Locale("en", "EN");
                        NumberFormat en = NumberFormat.getInstance(localeEN);

                        edInsurrance.setText("");
                        dependentt = Long.parseLong(dependent);
                        tvFamily.setText("" + en.format(3600000 * dependentt));
                        z = (long) Double.parseDouble(salaryGrossNet);
                        TaxBHXH = (z * 8) / 100;
                        if (TaxBHXH > 2384000) {
                            TaxBHXH = 2384000;
                        }
                        TaxBHYT = (long) ((z * 1.5) / 100);
                        if (TaxBHYT > 447000) {
                            TaxBHYT = 447000;
                        }

                        TaxBHTN = (z * 1) / 100;
                        if (btnOne.isChecked() && TaxBHTN > 836000) {
                            TaxBHTN = 836000;
                        }
                        if (btnTwo.isChecked() && TaxBHTN > 742000) {
                            TaxBHTN = 742000;
                        }
                        if (btnThree.isChecked() && TaxBHTN > 650000) {
                            TaxBHTN = 650000;
                        }
                        if (btnFour.isChecked() && TaxBHTN > 548000) {
                            TaxBHTN = 548000;
                        }

                        TotalInsurance = TaxBHYT + TaxBHXH + TaxBHTN;
                        TaxThunhaptruocthue = z - (TaxBHTN + TaxBHXH + TaxBHYT);//THU NHAP TRUOC THUE
                        tnct = TaxThunhaptruocthue - 9000000 - 3600000 * dependentt;

                        if (tnct < 0) {
                            tncn1 = 0;
                            tncn2 = 0;
                            tncn4 = 0;
                            tncn3 = 0;
                            tncn5 = 0;
                            tncn6 = 0;
                            tncn7 = 0;
                        }
                        if (tnct > 0 && tnct <= 5000000) {
                            tncn = (tnct * 5) / 100;
                            tncn1 = tncn;
                            tncn2 = tncn3 = tncn4 = tncn5 = tncn6 = tncn7 = 0;

                        }

                        if (tnct > 5000000 && tnct <= 10000000) {
                            tncn = (long) (0.25 * 1000000 + (tnct - 5 * 1000000) * 10 / 100);
                            tncn1 = 250000;
                            tncn2 = tncn - 250000;
                            tncn3 = tncn4 = tncn5 = tncn6 = tncn7 = 0;

                        }
                        if (tnct > 10000000 && tnct <= 18000000) {
                            tncn = (long) (0.75 * 1000000 + (tnct - 5 * 1000000) * 15 / 100);
                            tncn1 = 250000;
                            tncn2 = 500000;
                            tncn3 = tncn - 250000 - 500000;
                            tncn4 = tncn5 = tncn6 = tncn7 = 0;

                        }

                        if (tnct > 18000000 && tnct <= 32000000) {
                            tncn = (long) (1.95 * 1000000 + (tnct - 18 * 1000000) * 20 / 100);
                            tncn1 = 250000;
                            tncn2 = 500000;
                            tncn3 = 1200000;
                            tncn4 = tncn - 250000 - 500000 - 1200000;
                            tncn5 = tncn6 = tncn7 = 0;

                        }

                        if (tnct > 32000000 && tnct <= 52000000) {
                            tncn = (long) (4.75 * 1000000 + (tnct - 32 * 1000000) * 20 / 100);
                            tncn1 = 250000;
                            tncn2 = 500000;
                            tncn3 = 1200000;
                            tncn4 = 2800000;
                            tncn5 = tncn - 250000 - 500000 - 120000 - 2800000;
                            tncn6 = tncn7 = 0;

                        }

                        if (tnct > 52000000 && tnct <= 80000000) {
                            tncn = (long) (9.75 * 1000000 + (tnct - 52 * 1000000) * 30 / 100);
                            tncn1 = 250000;
                            tncn2 = 500000;
                            tncn3 = 1200000;
                            tncn4 = 2800000;
                            tncn5 = 5000000;
                            tncn6 = tncn - 250000 - 500000 - 120000 - 2800000 - 5000000;
                            tncn7 = 0;

                        }

                        if (tnct > 80000000) {
                            tncn = (long) (18.15 * 1000000 + (tnct - 80 * 1000000) * 35 / 100);
                            tncn1 = 250000;
                            tncn2 = 500000;
                            tncn3 = 1200000;
                            tncn4 = 2800000;
                            tncn5 = 5000000;
                            tncn6 = 8400000;
                            tncn7 = tncn - 250000 - 500000 - 1200000 - 2800000 - 5000000 - 8400000;
                        }

                        Net = TaxThunhaptruocthue - tncn;
                        IncomeTax = tncn1 + tncn2 + tncn3 + tncn4 + tncn5 + tncn6 + tncn7;

                        long BHXH_NTD = z * 17 / 100; // bao hien xa hoi nha tuyen dung dong
                        if (BHXH_NTD > 4726000) {
                            BHXH_NTD = 4726000;
                        }
                        long bhtnld = (long) (z * 0.5 / 100);  // bảo hiểm tai nạn lao động
                        if (bhtnld > 139000) {
                            bhtnld = 139000;
                        }
                        long bhyt_ntd = z * 3 / 100;  // bảo hiểm y tế
                        if (bhyt_ntd > 834000) {
                            bhyt_ntd = 834000;
                        }
                        long bhtn_ntd = z * 1 / 100;  // bảo hiểm thất nghiệp
                        if (bhtn_ntd > 836000) {
                            bhtn_ntd = 836000;
                        }

                        TotalTaxInsurance = (long) (z + BHXH_NTD + bhtnld + bhtn_ntd + bhyt_ntd);


                        tvInsurrance.setText("" + en.format(TotalInsurance));
                        tvTax.setText("" + en.format(TotalTaxInsurance));
                        tvIncomeTax.setText("" + en.format(IncomeTax));
                        TNTT.setText("" + en.format(TaxThunhaptruocthue));
                        TNCT.setText("" + en.format(tnct));
                        tvBHTN_NTD.setText("" + en.format(bhtn_ntd));
                        tvBHTNLD_NTD.setText("" + en.format(bhtnld));
                        tvBHXH_NTD.setText("" + en.format(BHXH_NTD));
                        tvBHYT_NTD.setText("" + en.format(bhyt_ntd));
                        tvGrossSalary.setText("" + en.format(z));
                        tvNetSalary.setText("" + en.format(Net));
                        TTNCN.setText("" + en.format(IncomeTax));
                        tvGross.setText("" + en.format(z));
                        TaxTNCN5.setText("" + en.format(tncn1));
                        TaxTNCN10.setText("" + en.format(tncn2));
                        TaxTNCN15.setText("" + en.format(tncn3));
                        TaxTNCN20.setText("" + en.format(tncn4));
                        TaxTNCN25.setText("" + en.format(tncn5));
                        TaxTNCN30.setText("" + en.format(tncn6));
                        TaxTNCN35.setText("" + en.format(tncn7));
                        tvSocialInsurance.setText("" + en.format(TaxBHXH));
                        tvHealthInsurance.setText("" + en.format(TaxBHYT));
                        tvUnemployment.setText("" + en.format(TaxBHTN));

                        tvNet.setText("" + en.format(Net));
                    }

                    if (btnOther.isChecked()) {
                        Locale localeEN = new Locale("en", "EN");
                        NumberFormat en = NumberFormat.getInstance(localeEN);
                        if (other.equals("")) {
                            Toast.makeText(getContext(), "Vui lòng nhập số tiền đóng bảo hiểm khác", Toast.LENGTH_SHORT).show();
                        } else {
                            dependentt = (long) Double.parseDouble(dependent);
                            in = (long) Double.parseDouble(salaryGrossNet);
                            long salaryother = Long.parseLong(other);
                            TaxBHXH = (salaryother * 8) / 100;
                            if (TaxBHXH > 2384000) {
                                TaxBHXH = 2384000;
                            }
                            TaxBHYT = (long) ((salaryother * 1.5) / 100);
                            if (TaxBHYT > 447000) {
                                TaxBHYT = 447000;
                            }

                            TaxBHTN = (salaryother * 1) / 100;
                            if (btnOne.isChecked() && TaxBHTN > 836000) {
                                TaxBHTN = 836000;
                            }
                            if (btnTwo.isChecked() && TaxBHTN > 742000) {
                                TaxBHTN = 742000;
                            }
                            if (btnThree.isChecked() && TaxBHTN > 650000) {
                                TaxBHTN = 650000;
                            }
                            if (btnFour.isChecked() && TaxBHTN > 548000) {
                                TaxBHTN = 548000;
                            }
                            tnct = in - (9000000 + 3600000 * dependentt);
                            TotalInsurance = TaxBHYT + TaxBHXH + TaxBHTN;
                            TaxThunhaptruocthue = in - TotalInsurance;//THU NHAP TRUOC THUE

                            if (tnct < 0) {
                                tncn1 = 0;
                                tncn2 = 0;
                                tncn4 = 0;
                                tncn3 = 0;
                                tncn5 = 0;
                                tncn6 = 0;
                                tncn7 = 0;
                            }
                            if (tnct > 0 && tnct <= 5000000) {
                                tncn = (tnct * 5) / 100;
                                tncn1 = tncn;
                                tncn2 = tncn3 = tncn4 = tncn5 = tncn6 = tncn7 = 0;

                            }

                            if (tnct > 5000000 && tnct <= 10000000) {
                                tncn = (long) (0.25 * 1000000 + (tnct - 5 * 1000000) * 10 / 100);
                                tncn1 = 250000;
                                tncn2 = tncn - 250000;
                                tncn3 = tncn4 = tncn5 = tncn6 = tncn7 = 0;

                            }
                            if (tnct > 10000000 && tnct <= 18000000) {
                                tncn = (long) (0.75 * 1000000 + (tnct - 5 * 1000000) * 15 / 100);
                                tncn1 = 250000;
                                tncn2 = 500000;
                                tncn3 = tncn - 250000 - 500000;
                                tncn4 = tncn5 = tncn6 = tncn7 = 0;

                            }

                            if (tnct > 18000000 && tnct <= 32000000) {
                                tncn = (long) (1.95 * 1000000 + (tnct - 18 * 1000000) * 20 / 100);
                                tncn1 = 250000;
                                tncn2 = 500000;
                                tncn3 = 1200000;
                                tncn4 = tncn - 250000 - 500000 - 1200000;
                                tncn5 = tncn6 = tncn7 = 0;

                            }

                            if (tnct > 32000000 && tnct <= 52000000) {
                                tncn = (long) (4.75 * 1000000 + (tnct - 32 * 1000000) * 20 / 100);
                                tncn1 = 250000;
                                tncn2 = 500000;
                                tncn3 = 1200000;
                                tncn4 = 2800000;
                                tncn5 = tncn - 250000 - 500000 - 120000 - 2800000;
                                tncn6 = tncn7 = 0;

                            }

                            if (tnct > 52000000 && tnct <= 80000000) {
                                tncn = (long) (9.75 * 1000000 + (tnct - 52 * 1000000) * 30 / 100);
                                tncn1 = 250000;
                                tncn2 = 500000;
                                tncn3 = 1200000;
                                tncn4 = 2800000;
                                tncn5 = 5000000;
                                tncn6 = tncn - 250000 - 500000 - 120000 - 2800000 - 5000000;
                                tncn7 = 0;

                            }

                            if (tnct > 80000000) {
                                tncn = (long) (18.15 * 1000000 + (tnct - 80 * 1000000) * 35 / 100);
                                tncn1 = 250000;
                                tncn2 = 500000;
                                tncn3 = 1200000;
                                tncn4 = 2800000;
                                tncn5 = 5000000;
                                tncn6 = 8400000;
                                tncn7 = tncn - 250000 - 500000 - 120000 - 2800000 - 5000000 - 8400000;


                            }


                            Net = (TaxThunhaptruocthue - tncn);

                            IncomeTax = tncn1 + tncn2 + tncn3 + tncn4 + tncn5 + tncn6 + tncn7;


                            double BHXH_NTD = salaryother * 17 / 100; // bao hien xa hoi nha tuyen dung dong
                            if (BHXH_NTD > 4726000) {
                                BHXH_NTD = 4726000;
                            }
                            double bhtnld = salaryother * 0.5 / 100;  // bảo hiểm tai nạn lao động
                            if (bhtnld > 139000) {
                                bhtnld = 139000;
                            }
                            double bhyt_ntd = salaryother * 3 / 100;  // bảo hiểm y tế
                            if (bhyt_ntd > 834000) {
                                bhyt_ntd = 834000;
                            }
                            double bhtn_ntd = salaryother * 1 / 100;  // bảo hiểm thất nghiệp
                            if (bhtn_ntd > 836000) {
                                bhtn_ntd = 836000;
                            }


                            TotalTaxInsurance = (long) (in + BHXH_NTD + bhtnld + bhtn_ntd + bhyt_ntd);


                            tvFamily.setText("" + en.format(3600000 * dependentt));
                            tvIncomeTax.setText("" + en.format(IncomeTax));
                            tvInsurrance.setText("" + en.format(TotalInsurance));
                            tvGrossSalary.setText("" + en.format(in));
                            tvNetSalary.setText("" + en.format(Net));
                            tvGross.setText("" + en.format(in));

                            tvBHXH_NTD.setText("" + en.format(BHXH_NTD));
                            tvBHTNLD_NTD.setText("" +en.format( bhtnld));
                            tvBHYT_NTD.setText("" + en.format(bhyt_ntd));
                            tvBHTN_NTD.setText("" + en.format(bhtn_ntd));


                            TNTT.setText("" +en.format( TaxThunhaptruocthue));
                            TaxTNCN5.setText("" + en.format(tncn1));
                            TaxTNCN10.setText("" + en.format(tncn2));
                            TTNCN.setText("" + en.format(IncomeTax));
                            TaxTNCN15.setText("" +en.format( tncn3));
                            TaxTNCN20.setText("" + en.format(tncn4));
                            TaxTNCN25.setText("" + en.format(tncn5));
                            TaxTNCN30.setText("" + en.format(tncn6));
                            TaxTNCN35.setText("" + en.format(tncn7));
                            TNCT.setText("" + en.format(tnct));
                            tvSocialInsurance.setText("" + en.format(TaxBHXH));
                            tvHealthInsurance.setText("" + en.format(TaxBHYT));
                            tvUnemployment.setText("" + en.format(TaxBHTN));
                            tvTax.setText("" + en.format(TotalTaxInsurance));
                            tvNet.setText("" + en.format(Net));
                        }
                    }


                }
            }

        });



        btnNetGross.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                Locale localeEN = new Locale("en", "EN");
                NumberFormat en = NumberFormat.getInstance(localeEN);
                btnGrossNet.setBackgroundColor(getResources().getColor(R.color.white));
                btnNetGross.setBackgroundColor(getResources().getColor(R.color.yellow));
                btnGrossNet.setTextColor(getResources().getColor(R.color.yellow));
                btnNetGross.setTextColor(getResources().getColor(R.color.white));
                String salaryGrossNet = edSalary.getText().toString();
                String other = edInsurrance.getText().toString();
                String dependent = edDependent.getText().toString();

                if (salaryGrossNet.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập lương của bạn", Toast.LENGTH_SHORT).show();
                } else if (dependent.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập số người phụ thuộc", Toast.LENGTH_SHORT).show();
                } else {
                    if (btnSalary.isChecked()) {
                        edInsurrance.setText("");

                        String depentt = edDependent.getText().toString();
                        long dependentt = Long.parseLong(depentt);


                        tvFamily.setText("" + en.format((3600000 * dependentt)));
                        in = (long) Double.parseDouble(salaryGrossNet);
                        long tnqd = in - (9000000 + 3600000 * dependentt);
                        long tntt, tncn = 0, tnct = 0, tncn1 = 0, tncn2 = 0, tncn3 = 0, tncn4 = 0, tncn5 = 0, tncn6 = 0, tncn7 = 0;

                        if (tnqd > 0 && tnqd <= 4750000) {
                            tnct = tnqd * 100 / 95;
                        }
                        if (tnqd > 4750000 && tnqd <= 9250000) {
                            tnct = (tnqd - 250000) * 100 / 90;
                        }
                        if (tnqd > 9250000 && tnqd <= 16050000) {
                            tnct = (tnqd - 750000) * 100 / 85;
                        }
                        if (tnqd > 16050000 && tnqd <= 27250000) {
                            tnct = (tnqd - 1650000) * 100 / 80;
                        }
                        if (tnqd > 27250000 && tnqd <= 42250000) {
                            tnct = (tnqd - 3250000) * 100 / 75;
                        }
                        if (tnqd > 42250000 && tnqd <= 61850000) {
                            tnct = (tnqd - 5850000) * 100 / 70;
                        }
                        if (tnqd > 61850000) {
                            tnct = (tnqd - 9850000) * 100 / 65;
                        }

                        if (tnqd < 0) {
                            tnct = 0;
                        }

                        tntt = tnct + 9000000 + 3600000 * dependentt;

                        if (tnct == 0) {
                            tncn = tncn1 = tncn2 = tncn3 = tncn4 = tncn5 = tncn6 = tncn7 = 0;
                        }

                        if (tnct > 0 && tnct <= 5000000) {
                            tncn = tnct * 5 / 100;
                            tncn1 = (long) tncn;
                            tncn2 = (long) (tncn3 = tncn4 = tncn5 = tncn6 = tncn7 = 0);
                        }
                        if (tnct > 5000000 && tnct <= 10000000) {
                            tncn = (long) (0.25 * 1000000 + (tnct - 5 * 1000000) * 10 / 100);
                            tncn1 = 250000;
                            tncn2 = (long) (tncn - 250000);
                            tncn3 = (long) (tncn4 = tncn5 = tncn6 = tncn7 = 0);
                        }
                        if (tnct > 10000000 && tnct <= 18000000) {
                            tncn = (long) (0.75 * 1000000 + (tnct - 10 * 1000000) * 15 / 100);
                            tncn1 = 250000;
                            tncn2 = 500000;
                            tncn3 = (long) (tncn - 250000 - 500000);
                            tncn4 = (long) (tncn5 = tncn6 = tncn7 = 0);
                        }
                        if (tnct > 18000000 && tnct <= 32000000) {
                            tncn = (long) (1.95 * 1000000 + (tnct - 18 * 1000000) * 20 / 100);
                            tncn1 = 250000;
                            tncn2 = 500000;
                            tncn3 = 1200000;
                            tncn4 = (long) (tncn - 250000 - 500000 - 1200000);
                            tncn5 = (long) (tncn6 = tncn7 = 0);
                        }
                        if (tnct > 32000000 && tnct <= 52000000) {
                            tncn = (long) (4.75 * 1000000 + (tnct - 32 * 1000000) * 20 / 100);
                            tncn1 = 250000;
                            tncn2 = 500000;
                            tncn3 = 1200000;
                            tncn4 = 2800000;
                            tncn5 = (long) (tncn - 250000 - 500000 - 1200000 - 2800000);
                            tncn6 = (long) (tncn7 = 0);
                        }
                        if (tnct > 52000000 && tnct <= 80000000) {
                            tncn = (long) (9.75 * 1000000 + (tnct - 52 * 1000000) * 30 / 100);
                            tncn1 = 250000;
                            tncn2 = 500000;
                            tncn3 = 1200000;
                            tncn4 = 2800000;
                            tncn5 = 5000000;
                            tncn6 = (long) (tncn - 250000 - 500000 - 1200000 - 2800000 - 5000000);
                            tncn7 = 0;
                        }
                        if (tnct > 80000000) {
                            tncn = (long) (18.15 * 1000000 + (tnct - 80 * 1000000) * 35 / 100);
                            tncn1 = 250000;
                            tncn2 = 500000;
                            tncn3 = 1200000;
                            tncn4 = 2800000;
                            tncn5 = 5000000;
                            tncn6 = 8400000;
                            tncn7 = (long) (tncn - 250000 - 500000 - 1200000 - 2800000 - 5000000 - 8400000);
                        }

                        if (bhxh == 0 && bhyt == 0 && bhtn == 0) {

                            long gross = (long) ((in + tncn) / 0.895);
                            bhxh = (gross * 8 / 100);
                            if (bhxh > 2224000) {
                                bhxh = 2224000;
                            }
                            bhyt = (long) (gross * 1.5 / 100);
                            if (bhyt > 417000) {
                                bhyt = 417000;
                            }
                            bhtn = (gross * 1 / 100);
                            if (btnOne.isChecked() && bhtn > 836000) {
                                bhtn = 836000;
                            }
                            if (btnTwo.isChecked() && bhtn > 742000) {
                                bhtn = 742000;
                            }
                            if (btnThree.isChecked() && bhtn > 650000) {
                                bhtn = 650000;
                            }
                            if (btnFour.isChecked() && bhtn > 584000) {
                                bhtn = 584000;
                            }


                            gross = (long) (in + tncn + bhxh + bhyt + bhtn);
                            tvGrossSalary.setText("" + en.format(gross));
                        } else {
                            long gross = (long) (in + bhxh + bhyt + bhtn + tncn);
                            tvGrossSalary.setText("" + en.format(gross));
                        }

                        if (btnSalary.isChecked()) {
                            double gross = (in + tncn) / 0.895;
                            bhxh_ntd = (long) (gross * 17 / 100);
                            if (bhxh_ntd > 4726000) {
                                bhxh_ntd = 4726000;
                            }
                            bhtnld = (long) (gross * 0.5 / 100);
                            if (bhtnld > 139000) {
                                bhtnld = 139000;
                            }
                            bhyt_ntd = (long) (gross * 3 / 100);
                            if (bhyt_ntd > 834000) {
                                bhyt_ntd = 834000;
                            }
                            bhtn_ntd = (long) (gross * 1 / 100);
                            if (bhtn_ntd > 836000) {
                                bhtn_ntd = 836000;
                            }
                        }

                        long TotalInsurance = bhtn + bhyt + bhxh;

                        TotalTaxInsurance = (long) (bhxh_ntd + bhtnld + bhtn_ntd + bhyt_ntd);


                        IncomeTax = tncn1 + tncn2 + tncn3 + tncn4 + tncn5 + tncn6 + tncn7;
                        tvIncomeTax.setText("" + en.format(IncomeTax));

                        TaxThunhaptruocthue = in - (TaxBHTN + TaxBHXH + TaxBHYT);//THU NHAP TRUOC THUE

                        TNTT.setText("" + en.format(tntt));
                        TNCT.setText("" + en.format(tnct));


                        tvInsurrance.setText("" + en.format(TotalInsurance));
                        long gross = (long) (in + bhxh + bhyt + bhtn + tncn);
//                        tvGrossSalary.setText("" + in);
                        tvNetSalary.setText("" + en.format(in));
                        tvGross.setText("" + en.format(gross));
                        tvSocialInsurance.setText("" + en.format(bhxh));
                        tvHealthInsurance.setText("" + en.format(bhyt));
                        TaxTNCN5.setText("" + en.format(tncn1));
                        TaxTNCN10.setText("" + en.format(tncn2));
                        TaxTNCN15.setText("" + en.format(tncn3));
                        TaxTNCN20.setText("" + en.format(tncn4));
                        TaxTNCN25.setText("" + en.format(tncn5));
                        TaxTNCN30.setText("" + en.format(tncn6));
                        TaxTNCN35.setText("" + en.format(tncn7));
                        TTNCN.setText("" + en.format(IncomeTax));

                        tvUnemployment.setText("" + en.format(bhtn));
                        tvBHTN_NTD.setText("" + en.format(bhtn_ntd));
                        tvBHTNLD_NTD.setText("" + en.format(bhtnld));
                        tvBHXH_NTD.setText("" + en.format(bhxh_ntd));
                        tvBHYT_NTD.setText("" + en.format(bhyt_ntd));
                        tvTax.setText("" + en.format((gross + bhxh_ntd + bhtn_ntd + bhyt_ntd + bhtnld)));
                        tvNet.setText("" + en.format(in));
                    }

                    if (btnOther.isChecked()) {
                        if (other.equals("")) {
                            Toast.makeText(getContext(), "Vui lòng nhập số tiền đóng bảo hiểm ngoài", Toast.LENGTH_SHORT).show();
                        } else {
                            in = (long) Double.parseDouble(salaryGrossNet);
                            String otherrr = edInsurrance.getText().toString();
                            String depentt = edDependent.getText().toString();
                            long depent = Long.parseLong(depentt);

                            z = (long) Double.parseDouble(otherrr);
                            bhxh = ((z * 8) / 100);
                            if (bhxh > 2384000) {
                                bhxh = 2384000;
                            }
                            bhyt = (long) ((z * 1.5) / 100);
                            if (bhyt > 447000) {
                                bhyt = 447000;
                            }

                            bhtn = ((z * 1) / 100);
                            if (btnOne.isChecked() && bhtn > 836000) {
                                bhtn = 836000;
                            }
                            if (btnTwo.isChecked() && bhtn > 742000) {
                                bhtn = 742000;
                            }
                            if (btnThree.isChecked() && bhtn > 650000) {
                                bhtn = 650000;
                            }
                            if (btnFour.isChecked() && bhtn > 584000) {
                                bhtn = 584000;
                            }

                            long tongBaohiem = bhxh + bhyt + bhtn;


                            long tnqd = in - (9000000 + 3600000 * depent);
                            long tntt, tncn = 0, tnct = 0, tncn1 = 0, tncn2 = 0, tncn3 = 0, tncn4 = 0, tncn5 = 0, tncn6 = 0, tncn7 = 0;

                            if (tnqd > 0 && tnqd <= 4750000) {
                                tnct = tnqd * 100 / 95;
                            }
                            if (tnqd > 4750000 && tnqd <= 9250000) {
                                tnct = (tnqd - 250000) * 100 / 90;
                            }
                            if (tnqd > 9250000 && tnqd <= 16050000) {
                                tnct = (tnqd - 750000) * 100 / 85;
                            }
                            if (tnqd > 16050000 && tnqd <= 27250000) {
                                tnct = (tnqd - 1650000) * 100 / 80;
                            }
                            if (tnqd > 27250000 && tnqd <= 42250000) {
                                tnct = (tnqd - 3250000) * 100 / 75;
                            }
                            if (tnqd > 42250000 && tnqd <= 61850000) {
                                tnct = (tnqd - 5850000) * 100 / 70;
                            }
                            if (tnqd > 61850000) {
                                tnct = (tnqd - 9850000) * 100 / 65;
                            }

                            if (tnqd < 0) {
                                tnct = 0;
                            }


                            if (tnct == 0) {
                                tncn = tncn1 = tncn2 = tncn3 = tncn4 = tncn5 = tncn6 = tncn7 = 0;
                            }

                            if (tnct > 0 && tnct <= 5000000) {
                                tncn = tnct * 5 / 100;
                                tncn1 = (long) tncn;
                                tncn2 = (long) (tncn3 = tncn4 = tncn5 = tncn6 = tncn7 = 0);
                            }
                            if (tnct > 5000000 && tnct <= 10000000) {
                                tncn = (long) (0.25 * 1000000 + (tnct - 5 * 1000000) * 10 / 100);
                                tncn1 = 250000;
                                tncn2 = (long) (tncn - 250000);
                                tncn3 = (long) (tncn4 = tncn5 = tncn6 = tncn7 = 0);
                            }
                            if (tnct > 10000000 && tnct <= 18000000) {
                                tncn = (long) (0.75 * 1000000 + (tnct - 10 * 1000000) * 15 / 100);
                                tncn1 = 250000;
                                tncn2 = 500000;
                                tncn3 = (long) (tncn - 250000 - 500000);
                                tncn4 = (long) (tncn5 = tncn6 = tncn7 = 0);
                            }
                            if (tnct > 18000000 && tnct <= 32000000) {
                                tncn = (long) (1.95 * 1000000 + (tnct - 18 * 1000000) * 20 / 100);
                                tncn1 = 250000;
                                tncn2 = 500000;
                                tncn3 = 1200000;
                                tncn4 = (long) (tncn - 250000 - 500000 - 1200000);
                                tncn5 = (long) (tncn6 = tncn7 = 0);
                            }
                            if (tnct > 32000000 && tnct <= 52000000) {
                                tncn = (long) (4.75 * 1000000 + (tnct - 32 * 1000000) * 20 / 100);
                                tncn1 = 250000;
                                tncn2 = 500000;
                                tncn3 = 1200000;
                                tncn4 = 2800000;
                                tncn5 = (long) (tncn - 250000 - 500000 - 1200000 - 2800000);
                                tncn6 = (long) (tncn7 = 0);
                            }
                            if (tnct > 52000000 && tnct <= 80000000) {
                                tncn = (long) (9.75 * 1000000 + (tnct - 52 * 1000000) * 30 / 100);
                                tncn1 = 250000;
                                tncn2 = 500000;
                                tncn3 = 1200000;
                                tncn4 = 2800000;
                                tncn5 = 5000000;
                                tncn6 = (long) (tncn - 250000 - 500000 - 1200000 - 2800000 - 5000000);
                                tncn7 = 0;
                            }
                            if (tnct > 80000000) {
                                tncn = (long) (18.15 * 1000000 + (tnct - 80 * 1000000) * 35 / 100);
                                tncn1 = 250000;
                                tncn2 = 500000;
                                tncn3 = 1200000;
                                tncn4 = 2800000;
                                tncn5 = 5000000;
                                tncn6 = 8400000;
                                tncn7 = tncn - 250000 - 500000 - 1200000 - 2800000 - 5000000 - 8400000;
                            }
                                tntt = tnct + 9000000 + 3600000 * depent;

                                long bhxh_ntd = z * 17 / 100; // bao hien xa hoi nha tuyen dung dong
                                if (bhxh_ntd > 4726000) {
                                    bhxh_ntd = 4726000;
                                }
                                long bhtnld = (long) (z * 0.5 / 100);  // bảo hiểm tai nạn lao động
                                if (bhtnld > 139000) {
                                    bhtnld = 139000;
                                }
                                long bhyt_ntd = z * 3 / 100;  // bảo hiểm y tế
                                if (bhyt_ntd > 834000) {
                                    bhyt_ntd = 834000;
                                }
                                long bhtn_ntd = z * 1 / 100;  // bảo hiểm thất nghiệp
                                if (bhtn_ntd > 836000) {
                                    bhtn_ntd = 836000;
                                }

                                IncomeTax = tncn1 + tncn2 + tncn3 + tncn4 + tncn5 + tncn6 + tncn7;


                                tvIncomeTax.setText("" + en.format(IncomeTax));

                                tvInsurrance.setText("" + en.format(tongBaohiem));
                                tvFamily.setText("" + en.format(3600000 * depent));
                                long grossSalary = (in + IncomeTax);
                                tvGrossSalary.setText("" + en.format(grossSalary + tongBaohiem));
                                tvNetSalary.setText("" + en.format(in));
                                tvNet.setText("" + en.format(in));
                                TNTT.setText("" + en.format(tntt));
                                TNCT.setText("" + en.format(tntt - 9000000 - 3600000 * depent));
                                tvGross.setText("" + en.format(grossSalary + tongBaohiem));
                                tvSocialInsurance.setText("" + en.format(bhxh));
                                tvHealthInsurance.setText("" + en.format(bhyt));
                                tvUnemployment.setText("" + en.format(bhtn));
                                tvBHTN_NTD.setText("" + en.format(bhtn_ntd));
                                tvBHTNLD_NTD.setText("" + en.format(bhtnld));
                                tvBHXH_NTD.setText("" + en.format(bhxh_ntd));
                                tvBHYT_NTD.setText("" + en.format(bhyt_ntd));
                                TaxTNCN5.setText("" + en.format(tncn1));
                                TTNCN.setText("" + en.format(IncomeTax));
                                TaxTNCN10.setText("" + en.format(tncn2));
                                TaxTNCN15.setText("" + en.format(tncn3));
                                TaxTNCN20.setText("" + en.format(tncn4));
                                TaxTNCN25.setText("" + en.format(tncn5));
                                TaxTNCN30.setText("" + en.format(tncn6));
                                TaxTNCN35.setText("" + en.format(tncn7));
                                tvTax.setText("" + en.format((grossSalary + tongBaohiem) + bhxh_ntd + bhtnld + bhtn_ntd + bhyt_ntd));
//                        tvNet.setText("" + Net);
                            }
                        }
                    }
                }

        });

        return view;
    }


    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

}
