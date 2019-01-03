import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {



        Socket s = new Socket("127.0.0.1",1201);

        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj numer indeksu: ");
        String msg = "";

        msg = br.readLine();

        DataOutputStream dataOutputStream = new DataOutputStream(os);
        dataOutputStream.writeBytes(msg + '\n');

        ObjectInputStream objectInputStream = new ObjectInputStream(is);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);

        Student student = (Student) objectInputStream.readObject();

        System.out.println("Dodac ocene? T/N");
        String answer = br.readLine();

        if(answer.equalsIgnoreCase("T")){
            System.out.println("Podaj ocene:");
            Integer mark = Integer.parseInt(br.readLine());
            student.addMark(mark);
        }

        objectOutputStream.writeObject(student);

        s.close();

    }
}
