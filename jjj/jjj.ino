int main_led = 9;
int green_led = 3;
int blue_led = 6;
int red_led = 5;
int yellow_led = 10;
int fan = 12;
boolean b_main=false,b_green=false,b_blue=false,b_red=false,b_yellow=false,b_fan=false; 

int val;

void setup()
{
   pinMode(main_led,OUTPUT);
   pinMode(green_led,OUTPUT);
   pinMode(blue_led,OUTPUT);
   pinMode(red_led,OUTPUT);
   pinMode(yellow_led,OUTPUT);
   pinMode(fan,OUTPUT);
     
   Serial.begin(115200);
    
}
void loop()
{   
    if(Serial.available()){
        val=Serial.read();        
        if(val=='M'){
            if(!b_main){
                digitalWrite(main_led,HIGH);
                b_main=true;
              }
            else{
              digitalWrite(main_led,LOW);
              b_main=false;
            }              
              //Serial.println("Open");              
          }
         else if(val=='G'){
            if(!b_green){
                digitalWrite(green_led,HIGH);
                b_green=true;
              }
            else{
              digitalWrite(green_led,LOW);
              b_green=false;
            }              
              //Serial.println("Open");              
          }
          else if(val=='B'){
            if(!b_blue){
                digitalWrite(blue_led,HIGH);
                b_blue=true;
              }
            else{
              digitalWrite(blue_led,LOW);
              b_blue=false;
            }              
              //Serial.println("Open");              
          }
          else if(val=='R'){
            if(!b_red){
                digitalWrite(red_led,HIGH);
                b_red=true;
              }
            else{
              digitalWrite(red_led,LOW);
              b_red=false;
            }              
              //Serial.println("Open");              
          }
          else if(val=='Y'){
            if(!b_yellow){
                digitalWrite(yellow_led,HIGH);
                b_yellow=true;
              }
            else{
              digitalWrite(yellow_led,LOW);
              b_yellow=false;
            }              
              //Serial.println("Open");              
          }
          else if(val=='F'){
            if(!b_fan){
                digitalWrite(fan,HIGH);
                b_fan=true;
              }
            else{
              digitalWrite(fan,LOW);
              b_fan=false;
            }              
              //Serial.println("Open");              
          }
          
          if(b_main) analogWrite(main_led,val);
          if(b_green) analogWrite(green_led,val);
          if(b_red) analogWrite(red_led,val);
          if(b_blue) analogWrite(blue_led,val);
          if(b_yellow) analogWrite(yellow_led,val);
            
          
    }
   
 }
