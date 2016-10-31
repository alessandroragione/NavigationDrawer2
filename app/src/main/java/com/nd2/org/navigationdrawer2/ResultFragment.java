package com.nd2.org.navigationdrawer2;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResultFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "AATestFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TEST_URL                   = "http://192.168.0.11:8080/icaretest/rest/operatori";
    private static final String ACTION_FOR_INTENT_CALLBACK = "THIS_IS_A_UNIQUE_KEY_WE_USE_TO_COMMUNICATE";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate(R.layout.fragment_result, container, false);
        //TableLayout tl = (TableLayout)myInflatedView.findViewById(R.id.TableOperatori);
        try
        {
            HttpGet httpGet = new HttpGet(new URI(TEST_URL));
            RestTask task = new RestTask(null, ACTION_FOR_INTENT_CALLBACK);
            task.execute(httpGet);
           // progress = ProgressDialog.show(getParent(), "Getting Data ...", "Waiting For Results...", true);
            String response = task.get();
            /*String response = "[\n" +
                    "{\n" +
                    "\"id\": 1,\n" +
                    "\"anagrafica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Giuseppina\",\n" +
                    "\"cognome\": \"Contaldo\",\n" +
                    "\"dataNascita\": \"1963-02-21\",\n" +
                    "\"dataNascitaString\": null,\n" +
                    "\"comuneNascita\": null,\n" +
                    "\"sesso\": \"F\",\n" +
                    "\"codiceFiscale\": null,\n" +
                    "\"partitaIva\": null,\n" +
                    "\"telefono\": null,\n" +
                    "\"cellulare\": \"327-5927563\",\n" +
                    "\"residenza\": {\n" +
                    "\"id\": 1,\n" +
                    "\"comune\": {\n" +
                    "\"id\": 5788,\n" +
                    "\"nome\": \"Trepuzzi\",\n" +
                    "\"codiceIstat\": \"075087\",\n" +
                    "\"codiceCatastale\": \"L383\",\n" +
                    "\"cap\": \"73019\",\n" +
                    "\"prefisso\": \"0832\",\n" +
                    "\"provincia\": {\n" +
                    "\"sigla\": \"LE\",\n" +
                    "\"codiceIstat\": \"075\",\n" +
                    "\"nome\": \"Lecce\",\n" +
                    "\"capoluogo\": \"Lecce\",\n" +
                    "\"regione\": {\n" +
                    "\"codiceIstat\": \"16\",\n" +
                    "\"nome\": \"Puglia\",\n" +
                    "\"capoluogo\": \"Bari\"\n" +
                    "}\n" +
                    "}\n" +
                    "},\n" +
                    "\"descrizione\": \"via don pio bianco\",\n" +
                    "\"numero\": \"11\",\n" +
                    "\"localita\": null,\n" +
                    "\"latitudine\": 40.402278,\n" +
                    "\"longitudine\": 18.078855\n" +
                    "},\n" +
                    "\"domicilio\": null,\n" +
                    "\"dataCreazione\": 1460474912000,\n" +
                    "\"dataCreazioneString\": null,\n" +
                    "\"email\": \"giusycontaldo@gmail.com\"\n" +
                    "},\n" +
                    "\"automunito\": false,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 4,\n" +
                    "\"nome\": \"Operatore Socio Assistenziale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "},\n" +
                    "\"curriculum\": null,\n" +
                    "\"note\": null,\n" +
                    "\"disponibilitaJson\": null,\n" +
                    "\"disponibilitaString\": null,\n" +
                    "\"dataInizioServizioString\": null,\n" +
                    "\"mesiServizio\": 0,\n" +
                    "\"giorniFestivi\": true,\n" +
                    "\"stato\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Disponibile\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"cssClass\": \"green\"\n" +
                    "},\n" +
                    "\"esperienza\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Basso\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"cssClass\": \"danger\"\n" +
                    "},\n" +
                    "\"servizi\": [\n" +
                    "{\n" +
                    "\"id\": 2,\n" +
                    "\"nome\": \"Aiuto nell'igiene personale (quotidiano)\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Aiuto per il bagno/doccia\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 4,\n" +
                    "\"nome\": \"Aiuto nel vestirsi\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 5,\n" +
                    "\"nome\": \"Preparazione pasti\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 6,\n" +
                    "\"nome\": \"Somministrazione pasti\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 7,\n" +
                    "\"nome\": \"Aiuto nella deambulazione\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 9,\n" +
                    "\"nome\": \"Aiuto nell'igiene ambientale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 10,\n" +
                    "\"nome\": \"Aiuto negli acquisti\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 11,\n" +
                    "\"nome\": \"Disbrigo pratiche\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "}\n" +
                    "],\n" +
                    "\"serviziJson\": null,\n" +
                    "\"serviziString\": null,\n" +
                    "\"dataCreazione\": 1460474912000,\n" +
                    "\"dataCreazioneString\": null,\n" +
                    "\"percentuale\": null,\n" +
                    "\"aerialDistance\": null,\n" +
                    "\"slotInteressatiJson\": null,\n" +
                    "\"retribuzioneOraria\": null\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 2,\n" +
                    "\"anagrafica\": {\n" +
                    "\"id\": 4,\n" +
                    "\"nome\": \"Emilia\",\n" +
                    "\"cognome\": \"Giancane Centonze\",\n" +
                    "\"dataNascita\": \"1992-02-09\",\n" +
                    "\"dataNascitaString\": null,\n" +
                    "\"comuneNascita\": null,\n" +
                    "\"sesso\": \"F\",\n" +
                    "\"codiceFiscale\": null,\n" +
                    "\"partitaIva\": null,\n" +
                    "\"telefono\": null,\n" +
                    "\"cellulare\": \"338-8154268\",\n" +
                    "\"residenza\": {\n" +
                    "\"id\": 2,\n" +
                    "\"comune\": {\n" +
                    "\"id\": 1698,\n" +
                    "\"nome\": \"Carmiano\",\n" +
                    "\"codiceIstat\": \"075014\",\n" +
                    "\"codiceCatastale\": \"B792\",\n" +
                    "\"cap\": \"73041\",\n" +
                    "\"prefisso\": \"0832\",\n" +
                    "\"provincia\": {\n" +
                    "\"sigla\": \"LE\",\n" +
                    "\"codiceIstat\": \"075\",\n" +
                    "\"nome\": \"Lecce\",\n" +
                    "\"capoluogo\": \"Lecce\",\n" +
                    "\"regione\": {\n" +
                    "\"codiceIstat\": \"16\",\n" +
                    "\"nome\": \"Puglia\",\n" +
                    "\"capoluogo\": \"Bari\"\n" +
                    "}\n" +
                    "}\n" +
                    "},\n" +
                    "\"descrizione\": \"via IV NOVEMBRE\",\n" +
                    "\"numero\": \"14\",\n" +
                    "\"localita\": null,\n" +
                    "\"latitudine\": 40.339692,\n" +
                    "\"longitudine\": 18.0596\n" +
                    "},\n" +
                    "\"domicilio\": null,\n" +
                    "\"dataCreazione\": 1461061729000,\n" +
                    "\"dataCreazioneString\": null,\n" +
                    "\"email\": \"emilygiancane@gmail.com\"\n" +
                    "},\n" +
                    "\"automunito\": true,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "},\n" +
                    "\"curriculum\": null,\n" +
                    "\"note\": null,\n" +
                    "\"disponibilitaJson\": null,\n" +
                    "\"disponibilitaString\": null,\n" +
                    "\"dataInizioServizioString\": null,\n" +
                    "\"mesiServizio\": 0,\n" +
                    "\"giorniFestivi\": true,\n" +
                    "\"stato\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Disponibile\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"cssClass\": \"green\"\n" +
                    "},\n" +
                    "\"esperienza\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Alto\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"cssClass\": \"success\"\n" +
                    "},\n" +
                    "\"servizi\": [\n" +
                    "{\n" +
                    "\"id\": 12,\n" +
                    "\"nome\": \"Sorveglianza infermeristica\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 13,\n" +
                    "\"nome\": \"Counseling ed interventi educativi\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 14,\n" +
                    "\"nome\": \"Prelievi ematici\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 15,\n" +
                    "\"nome\": \"Sostituzione catetere\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 16,\n" +
                    "\"nome\": \"Medicazioni\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "}\n" +
                    "}\n" +
                    "],\n" +
                    "\"serviziJson\": null,\n" +
                    "\"serviziString\": null,\n" +
                    "\"dataCreazione\": 1461061729000,\n" +
                    "\"dataCreazioneString\": null,\n" +
                    "\"percentuale\": null,\n" +
                    "\"aerialDistance\": 7726,\n" +
                    "\"slotInteressatiJson\": null,\n" +
                    "\"retribuzioneOraria\": null\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 3,\n" +
                    "\"anagrafica\": {\n" +
                    "\"id\": 7,\n" +
                    "\"nome\": \"Isabella\",\n" +
                    "\"cognome\": \"Amorosini\",\n" +
                    "\"dataNascita\": \"1968-07-21\",\n" +
                    "\"dataNascitaString\": null,\n" +
                    "\"comuneNascita\": null,\n" +
                    "\"sesso\": \"F\",\n" +
                    "\"codiceFiscale\": \"MRSSLL68L61L108P\",\n" +
                    "\"partitaIva\": null,\n" +
                    "\"telefono\": null,\n" +
                    "\"cellulare\": \"340-1591089\",\n" +
                    "\"residenza\": {\n" +
                    "\"id\": 5,\n" +
                    "\"comune\": {\n" +
                    "\"id\": 5788,\n" +
                    "\"nome\": \"Trepuzzi\",\n" +
                    "\"codiceIstat\": \"075087\",\n" +
                    "\"codiceCatastale\": \"L383\",\n" +
                    "\"cap\": \"73019\",\n" +
                    "\"prefisso\": \"0832\",\n" +
                    "\"provincia\": {\n" +
                    "\"sigla\": \"LE\",\n" +
                    "\"codiceIstat\": \"075\",\n" +
                    "\"nome\": \"Lecce\",\n" +
                    "\"capoluogo\": \"Lecce\",\n" +
                    "\"regione\": {\n" +
                    "\"codiceIstat\": \"16\",\n" +
                    "\"nome\": \"Puglia\",\n" +
                    "\"capoluogo\": \"Bari\"\n" +
                    "}\n" +
                    "}\n" +
                    "},\n" +
                    "\"descrizione\": \"VIA pirandello\",\n" +
                    "\"numero\": \"15\",\n" +
                    "\"localita\": null,\n" +
                    "\"latitudine\": 40.411428,\n" +
                    "\"longitudine\": 18.067455\n" +
                    "},\n" +
                    "\"domicilio\": null,\n" +
                    "\"dataCreazione\": null,\n" +
                    "\"dataCreazioneString\": null,\n" +
                    "\"email\": \"isabellaamorosini.ia@gmail.com\"\n" +
                    "},\n" +
                    "\"automunito\": true,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "},\n" +
                    "\"curriculum\": null,\n" +
                    "\"note\": null,\n" +
                    "\"disponibilitaJson\": null,\n" +
                    "\"disponibilitaString\": null,\n" +
                    "\"dataInizioServizioString\": null,\n" +
                    "\"mesiServizio\": 0,\n" +
                    "\"giorniFestivi\": false,\n" +
                    "\"stato\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Disponibile\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"cssClass\": \"green\"\n" +
                    "},\n" +
                    "\"esperienza\": {\n" +
                    "\"id\": 2,\n" +
                    "\"nome\": \"Medio\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"cssClass\": \"warning\"\n" +
                    "},\n" +
                    "\"servizi\": [\n" +
                    "{\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Aiuto nell'assunzione di farmaci o dei medicamenti\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 2,\n" +
                    "\"nome\": \"Aiuto nell'igiene personale (quotidiano)\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Aiuto per il bagno/doccia\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 4,\n" +
                    "\"nome\": \"Aiuto nel vestirsi\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 5,\n" +
                    "\"nome\": \"Preparazione pasti\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 6,\n" +
                    "\"nome\": \"Somministrazione pasti\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 7,\n" +
                    "\"nome\": \"Aiuto nella deambulazione\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 8,\n" +
                    "\"nome\": \"Accompagnamenti\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 9,\n" +
                    "\"nome\": \"Aiuto nell'igiene ambientale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 10,\n" +
                    "\"nome\": \"Aiuto negli acquisti\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 11,\n" +
                    "\"nome\": \"Disbrigo pratiche\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 3,\n" +
                    "\"nome\": \"Operatore Socio Sanitario\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 10,\n" +
                    "\"prezzoAzienda\": 6\n" +
                    "}\n" +
                    "}\n" +
                    "],\n" +
                    "\"serviziJson\": null,\n" +
                    "\"serviziString\": null,\n" +
                    "\"dataCreazione\": 1462956410000,\n" +
                    "\"dataCreazioneString\": null,\n" +
                    "\"percentuale\": null,\n" +
                    "\"aerialDistance\": null,\n" +
                    "\"slotInteressatiJson\": null,\n" +
                    "\"retribuzioneOraria\": null\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 6,\n" +
                    "\"anagrafica\": {\n" +
                    "\"id\": 10,\n" +
                    "\"nome\": \"Annalucia\",\n" +
                    "\"cognome\": \"Leone\",\n" +
                    "\"dataNascita\": \"1988-01-19\",\n" +
                    "\"dataNascitaString\": null,\n" +
                    "\"comuneNascita\": null,\n" +
                    "\"sesso\": \"F\",\n" +
                    "\"codiceFiscale\": null,\n" +
                    "\"partitaIva\": null,\n" +
                    "\"telefono\": null,\n" +
                    "\"cellulare\": \"328-7573155\",\n" +
                    "\"residenza\": {\n" +
                    "\"id\": 8,\n" +
                    "\"comune\": {\n" +
                    "\"id\": 1246,\n" +
                    "\"nome\": \"Squinzano\",\n" +
                    "\"codiceIstat\": \"075079\",\n" +
                    "\"codiceCatastale\": \"I930\",\n" +
                    "\"cap\": \"73018\",\n" +
                    "\"prefisso\": \"0832\",\n" +
                    "\"provincia\": {\n" +
                    "\"sigla\": \"LE\",\n" +
                    "\"codiceIstat\": \"075\",\n" +
                    "\"nome\": \"Lecce\",\n" +
                    "\"capoluogo\": \"Lecce\",\n" +
                    "\"regione\": {\n" +
                    "\"codiceIstat\": \"16\",\n" +
                    "\"nome\": \"Puglia\",\n" +
                    "\"capoluogo\": \"Bari\"\n" +
                    "}\n" +
                    "}\n" +
                    "},\n" +
                    "\"descrizione\": \"Via Tolstoj\",\n" +
                    "\"numero\": \"53\",\n" +
                    "\"localita\": null,\n" +
                    "\"latitudine\": 40.4285493,\n" +
                    "\"longitudine\": 18.046516\n" +
                    "},\n" +
                    "\"domicilio\": null,\n" +
                    "\"dataCreazione\": null,\n" +
                    "\"dataCreazioneString\": null,\n" +
                    "\"email\": \"annalucia_leone@libero.it\"\n" +
                    "},\n" +
                    "\"automunito\": true,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "},\n" +
                    "\"curriculum\": null,\n" +
                    "\"note\": null,\n" +
                    "\"disponibilitaJson\": null,\n" +
                    "\"disponibilitaString\": null,\n" +
                    "\"dataInizioServizioString\": null,\n" +
                    "\"mesiServizio\": 0,\n" +
                    "\"giorniFestivi\": true,\n" +
                    "\"stato\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Disponibile\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"cssClass\": \"green\"\n" +
                    "},\n" +
                    "\"esperienza\": {\n" +
                    "\"id\": 2,\n" +
                    "\"nome\": \"Medio\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"cssClass\": \"warning\"\n" +
                    "},\n" +
                    "\"servizi\": [\n" +
                    "{\n" +
                    "\"id\": 12,\n" +
                    "\"nome\": \"Sorveglianza infermeristica\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 13,\n" +
                    "\"nome\": \"Counseling ed interventi educativi\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 14,\n" +
                    "\"nome\": \"Prelievi ematici\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 15,\n" +
                    "\"nome\": \"Sostituzione catetere\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 16,\n" +
                    "\"nome\": \"Medicazioni\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Infermiere Professionale\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": null,\n" +
                    "\"prezzoAzienda\": null\n" +
                    "}\n" +
                    "}\n" +
                    "],\n" +
                    "\"serviziJson\": null,\n" +
                    "\"serviziString\": null,\n" +
                    "\"dataCreazione\": 1463388578000,\n" +
                    "\"dataCreazioneString\": null,\n" +
                    "\"percentuale\": null,\n" +
                    "\"aerialDistance\": null,\n" +
                    "\"slotInteressatiJson\": null,\n" +
                    "\"retribuzioneOraria\": null\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 7,\n" +
                    "\"anagrafica\": {\n" +
                    "\"id\": 11,\n" +
                    "\"nome\": \"Elena\",\n" +
                    "\"cognome\": \"Annino\",\n" +
                    "\"dataNascita\": \"1980-02-19\",\n" +
                    "\"dataNascitaString\": null,\n" +
                    "\"comuneNascita\": null,\n" +
                    "\"sesso\": \"F\",\n" +
                    "\"codiceFiscale\": null,\n" +
                    "\"partitaIva\": null,\n" +
                    "\"telefono\": null,\n" +
                    "\"cellulare\": \"380-7848399\",\n" +
                    "\"residenza\": {\n" +
                    "\"id\": 9,\n" +
                    "\"comune\": {\n" +
                    "\"id\": 1246,\n" +
                    "\"nome\": \"Squinzano\",\n" +
                    "\"codiceIstat\": \"075079\",\n" +
                    "\"codiceCatastale\": \"I930\",\n" +
                    "\"cap\": \"73018\",\n" +
                    "\"prefisso\": \"0832\",\n" +
                    "\"provincia\": {\n" +
                    "\"sigla\": \"LE\",\n" +
                    "\"codiceIstat\": \"075\",\n" +
                    "\"nome\": \"Lecce\",\n" +
                    "\"capoluogo\": \"Lecce\",\n" +
                    "\"regione\": {\n" +
                    "\"codiceIstat\": \"16\",\n" +
                    "\"nome\": \"Puglia\",\n" +
                    "\"capoluogo\": \"Bari\"\n" +
                    "}\n" +
                    "}\n" +
                    "},\n" +
                    "\"descrizione\": \"Via San Salvatore\",\n" +
                    "\"numero\": \"38\",\n" +
                    "\"localita\": null,\n" +
                    "\"latitudine\": 40.4040676,\n" +
                    "\"longitudine\": 18.015304\n" +
                    "},\n" +
                    "\"domicilio\": null,\n" +
                    "\"dataCreazione\": null,\n" +
                    "\"dataCreazioneString\": null,\n" +
                    "\"email\": \"elena.annino@libero.it\"\n" +
                    "},\n" +
                    "\"automunito\": true,\n" +
                    "\"qualifica\": {\n" +
                    "\"id\": 5,\n" +
                    "\"nome\": \"Ausiliario (senza titolo)\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"prezzoListino\": 6,\n" +
                    "\"prezzoAzienda\": 4.2\n" +
                    "},\n" +
                    "\"curriculum\": null,\n" +
                    "\"note\": null,\n" +
                    "\"disponibilitaJson\": null,\n" +
                    "\"disponibilitaString\": null,\n" +
                    "\"dataInizioServizioString\": null,\n" +
                    "\"mesiServizio\": 0,\n" +
                    "\"giorniFestivi\": false,\n" +
                    "\"stato\": {\n" +
                    "\"id\": 1,\n" +
                    "\"nome\": \"Disponibile\",\n" +
                    "\"descrizione\": null,\n" +
                    "\"cssClass\": \"green\"\n" +
                    "},\n" +
                    "\"esperienza\": null,\n" +
                    "\"servizi\": [],\n" +
                    "\"serviziJson\": null,\n" +
                    "\"serviziString\": null,\n" +
                    "\"dataCreazione\": 1464778266000,\n" +
                    "\"dataCreazioneString\": null,\n" +
                    "\"percentuale\": null,\n" +
                    "\"aerialDistance\": null,\n" +
                    "\"slotInteressatiJson\": null,\n" +
                    "\"retribuzioneOraria\": null\n" +
                    "}]";*/
            JSONArray jsonArray = new JSONArray(response);
            String id;
            String nome = "";
            String cognome = "";
            List<Operatori> Operatori = new ArrayList<Operatori>();
            for(int i=0; i < jsonArray.length(); i++) {
                Operatori operatore = new Operatori();
                JSONObject jsonOb = jsonArray.getJSONObject(i);
                id = jsonOb.getString("id");
                operatore.setId(id);
                JSONObject jsonObj = jsonOb.getJSONObject("anagrafica");
                nome = jsonObj.getString("nome");
                cognome = jsonObj.getString("cognome");
                operatore.setNome(nome);
                operatore.setCognome(cognome);
                Operatori.add(i,operatore);

            }
            final ListView mylist = (ListView) myInflatedView.findViewById(R.id.ListView);
            //for(int i = 0; i < Operatori.size(); i++){
            final OperatoriAdapter adapter = new OperatoriAdapter (getActivity(),android.R.layout.simple_expandable_list_item_2,Operatori);
            //final ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Operatori);
            //OperatoriAdapter adapter = new OperatoriAdapter(getActivity(), Operatori);
            mylist.setAdapter(adapter);
                /*TableRow tr = new TableRow(getActivity());
                tr.setId(100 + i);
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                TextView idTV = new TextView(getActivity());
                idTV.setId(200 + i);
                idTV.setText(String.valueOf(Operatori.get(i).getId()));
                tr.addView(idTV);
                TextView nomeTV = new TextView(getActivity());
                nomeTV.setId(300 + i);
                nomeTV.setText(String.valueOf(Operatori.get(i).getNome()));
                tr.addView(nomeTV);
                TextView cognomeTV = new TextView(getActivity());
                cognomeTV.setId(400 + i);
                cognomeTV.setText(String.valueOf(Operatori.get(i).getCognome()));
                tr.addView(cognomeTV);
                tl.addView(tr, i);*/

            //}
            //TextView IdView = (TextView)myInflatedView.findViewById(R.id.id);
            //IdView.setText(id);
        }
        catch (Exception e)
        {
            Log.e(TAG, e.getMessage());
        }
        System.out.println("Fragment creato");
        return myInflatedView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                   + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void populateResult(String id, String name) {
        /*TextView IdView = (TextView)getView().findViewById(R.id.id);
        TextView NameView = (TextView)getView().findViewById(R.id.name);
        IdView.setText(id);
        NameView.setText(name);*/
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
