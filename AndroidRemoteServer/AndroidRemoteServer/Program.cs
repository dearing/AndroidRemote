using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.Threading;
using System.IO;

namespace AndroidRemoteServer
{
    static class Program
    {
        public static string AppName;
        public static string Version;
        public static string LogFile;
        public static NotifyIcon ni;

        [STAThread]
        static void Main()
        {
            AppName = System.Reflection.Assembly.GetExecutingAssembly().GetName().Name;
            Version = System.Reflection.Assembly.GetExecutingAssembly().GetName().Version.ToString();
            LogFile = AppName + ".log";

            if (File.Exists(LogFile))
                File.Delete(LogFile);

            Engine.Log(String.Format("runing android server v{0} ({1})", Version,AppName));

            Networking n = new Networking(AndroidRemoteServer.Properties.Settings.Default.host, AndroidRemoteServer.Properties.Settings.Default.port);
            ThreadPool.QueueUserWorkItem(new WaitCallback(n.Start));

            Engine.Log(String.Format("server bound to host {0}:{1}",
                AndroidRemoteServer.Properties.Settings.Default.host,
                AndroidRemoteServer.Properties.Settings.Default.port));
            
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            ni = new NotifyIcon();
            ni.Visible = true;
            ni.Icon = AndroidRemoteServer.Properties.Resources.app;
            ni.Click += new EventHandler(ni_Click);

            Engine.Log("runing android server main thread");

            Application.Run();
        }
        
        static void ni_Click(object sender, EventArgs e)
        {
            Engine.Log("icon clicked; exiting application");
            ni.Visible = false;
            ni.Dispose();
            Application.Exit();
        }
    }
}
