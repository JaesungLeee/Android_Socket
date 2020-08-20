import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class socketServer {
    public static void main(String[] args) {
        try {
            // Server Socket 객체 생성
            ServerSocket server = new ServerSocket(55555);
            System.out.println("사용자 접속 대기");

            // Client와 접속 성공 시 Socket 객체 생성
            Socket socket = server.accept();
            System.out.println("Socket 생성");

            // Data 송신 (Server -> Client)
            InputStream input = socket.getInputStream();
            DataInputStream dis = new DataInputStream(input);

            // Data 수신 (Client -> Server)
            OutputStream output = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(output);

            // Server 송신 내용
            dos.writeInt(100);
            dos.writeDouble(11.1);
            dos.writeUTF("SERVER");
            System.out.println("전송 성공");

            // Server 수신 내용 추출
            int data1 = dis.readInt();
            double data2 = dis.readDouble();
            String data3 = dis.readUTF();
            System.out.println("수신 성공");

            // Socket 해제 & Server 해제
            socket.close();
            server.close();

            // Data 출력
            System.out.printf("data1 : %d\n" ,data1);
            System.out.printf("data2 : %f\n" ,data2);
            System.out.printf("data3 : %s\n" ,data3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
