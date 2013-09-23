using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Sockets;
using System.Threading;

namespace AndroidRemoteServer
{
    class Networking
    {

        private TcpListener listener;
        private TcpClient client;
        private NetworkStream ns;

        public bool Online { get; private set; }
        public string ip { get; private set; }
        public int Port { get; private set; }
        

        #region Events
        public event EventHandler<NetworkArgs> MessageReceived;
        protected virtual void OnMessageReceived(NetworkArgs e)
        {
            var handler = MessageReceived;
            if (handler != null)
                handler(this, e);
        }

        public event EventHandler<NetworkArgs> MessageSent;
        protected virtual void OnMessageSent(NetworkArgs e)
        {
            var handler = MessageSent;
            if (handler != null)
                handler(this, e);
        }
        #endregion Events

        public Networking(String ip, int Port)
        {
            this.ip = ip;
            this.Port = Port;
            this.listener = new TcpListener(IPAddress.Parse(ip), Port);
            
        }
        void ReadMessage()
        {
            StringBuilder sb = new StringBuilder();

            while (ns.DataAvailable)
                sb.Append(Convert.ToChar(ns.ReadByte()));
            OnMessageReceived(new NetworkArgs(client.Client.RemoteEndPoint.ToString(), sb.ToString()));
            Engine.ParsePayload(sb.ToString(), ref ns);
            //MessageReceived(this, new NetworkArgs(client.Client.RemoteEndPoint.ToString(), sb.ToString()));
        }

        public void Start(object o)
        {
            try
            {
                listener.Start();
                this.Online = true;
                while (Online)
                {
                    client = listener.AcceptTcpClient();
                    ns = client.GetStream();

                    while (true)
                    {
                        Thread.Sleep(50);
                        if (ns.CanRead)
                        {
                            if (ns.DataAvailable)
                            {
                                ReadMessage();
                                break;
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {

                throw;
            }
        }
    }
}
