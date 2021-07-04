package parabitccasbharat;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import javax.swing.AbstractAction;

import javax.swing.JButton;

import javax.swing.JFrame;


@SuppressWarnings("serial")

public class PbtQrReader extends JFrame {

String[] values;

	public PbtQrReader() {

values = new String[14];
                
		setTitle("Main frame");

		setLayout(new FlowLayout());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		add(new JButton(new AbstractAction("CAPTURE QR") {



			@Override

			public void actionPerformed(ActionEvent e) {

				final Thread thread = new Thread(new Runnable() {



					@Override

					public void run() {

						try (PbtQrCapture qr = new PbtQrCapture()) {

							aadharDet("QR code text is:\n" + qr.getResult()  + "");

						} catch (InterruptedException ex) {

							ex.printStackTrace();

						}

					};

				});

				thread.setDaemon(true);

				thread.start();

			}

		}));



		pack();

		//setVisible(true);

	}



	/*private void showMessage(String text) {

		System.out.println(text);

	}*/
        
        private void showMessage(String text){
    String ar[]={"uid","name","gender","yob","gname","house","lm","vtc","po","dist","subdist","state","pc","dob"};
    Pattern pattern;
    Matcher matcher;
    System.out.println(text);
    for(int i=0;i<ar.length;i++){
        pattern=Pattern.compile(ar[i]+"=\"(.[^\"]*)\"");
        matcher=pattern.matcher(text);
        if(matcher.find()){
            System.out.println(matcher.group(1));
           // jTextField1.setText(matcher.group(1));
        }
    } 
}
        public void aadharDet(String text)
        {
            String ar[] = {"uid", "name", "gender", "dateOfBirth", "careOf", "building", "street", "landmark", "vtcName", "poName", "districtName", "subDistrictName", "stateName", "pincode"};
            
            Pattern p;
            Matcher m;
            System.out.println(text);
            for (int i = 0; i < ar.length; i++)
            {
                p = Pattern.compile(ar[i] + "=\"(.[^\"]*)\"");
                m = p.matcher(text);
                if (m.find())
                {
                    System.out.println(ar[i] + " = " + m.group(1));
                    
                }
            }
        }
        

public void openCamera()
{
    final Thread thread = new Thread(new Runnable() {



					@Override

					public void run() {

						try (PbtQrCapture qr = new PbtQrCapture()) {

							aadharDet("QR code text is:\n" + qr.getResult()  + "");

						} catch (InterruptedException ex) {

							ex.printStackTrace();

						}

					};

				});

				thread.setDaemon(true);

				thread.start();
}

	public static void main(String[] args) {

		new PbtQrReader();

	}

}
