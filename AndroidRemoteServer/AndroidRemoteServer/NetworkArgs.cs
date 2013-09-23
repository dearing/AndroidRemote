using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AndroidRemoteServer
{
    class NetworkArgs : EventArgs
    {
        public String Message { get; set; }
        public String EndPoint { get; set; }
        public DateTime Timestamp { get; set; }


        public NetworkArgs(String Endpoint, String Message)
        {
            this.EndPoint = EndPoint;
            this.Message = Message;
            this.Timestamp = DateTime.Now;
        }
    }
}
