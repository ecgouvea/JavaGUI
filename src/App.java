import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        JFrame frame = new JFrame();
        // frame.setLayout(new FlowLayout());
        frame.setTitle("API Calls");
        frame.setSize(420, 420);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLUE);

        JButton button = new JButton("Call API");
        button.setBounds(10, 10, 100, 50);
        button.setVisible(true);

        JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(500, 500));
        
        ActionListener l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setText("Clicked");
                try {
                    HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/get")).GET()
                            .build();

                    HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
                    String body = response.body();
                    System.out.println(body);
                    textArea.setText(body);
                } catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            
        };

        button.addActionListener(l);

        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 200, 200);
        panel.add(button);
        panel.add(textArea);

        frame.add(panel);
        frame.pack();
    }
}
