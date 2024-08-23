package window;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class aaaa {
	public static void main(String[] args) {
        // GIF 파일의 경로를 설정합니다.
        String gifPath = "./datafiles/홍보/1.gif";

        // 원하는 이미지 크기를 설정합니다.
        int width = 400;  // 원하는 너비
        int height = 300; // 원하는 높이

        // JFrame을 생성합니다.
        JFrame frame = new JFrame("GIF Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ImageIcon을 사용하여 GIF를 로드합니다.
        ImageIcon gifIcon = new ImageIcon(gifPath);

        // ImageIcon에서 Image를 가져와 크기를 조정합니다.
        Image gifImage = gifIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);

        // 크기 조정된 이미지를 사용하여 새로운 ImageIcon을 생성합니다.
        ImageIcon scaledGifIcon = new ImageIcon(gifImage);

        // JLabel을 생성하고 크기 조정된 ImageIcon을 설정합니다.
        JLabel label = new JLabel(scaledGifIcon);

        // JLabel을 JFrame에 추가합니다.
        frame.add(label, BorderLayout.CENTER);

        // JFrame 크기를 맞춥니다.
        frame.pack();

        // JFrame을 화면에 표시합니다.
        frame.setVisible(true);
    }
}
