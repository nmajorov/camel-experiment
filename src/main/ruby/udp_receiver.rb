
require 'java'

  require 'socket'

java_package 'biz.majorov.camel'

  class UDPReceiver
    java_signature 'void listen(biz.majorov.camel.component.SocketHandler)'
    def listen(handler)
      s = UDPSocket.new
 
      port = 1234
       
      s.bind(nil, port)
      puts "start udp receiver on port #{port}"
       
     loop do
        text, sender = s.recvfrom(16)
       
        handler.messageReceived(text)
      end

    end

  end

  
=begin
   simple UDP receiver written in ruby
  
   **** how-to test UDP socket ****  
  
   irb
    2.1.1 :001 > require 'socket'
    => true 
    2.1.1 :002 > s = UDPSocket.new
    => #<UDPSocket:fd 9> 
     2.1.1 :003 > s.send("hello",0,'localhost',1234)
    => 5 
      
=end
 