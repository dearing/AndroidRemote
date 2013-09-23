using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using System.Windows.Forms;
using System.Diagnostics;
using System.Runtime.InteropServices;
using Settings = AndroidRemoteServer.Properties.Settings;

namespace AndroidRemoteServer
{
    public static class Engine
    {
        public static void ParsePayload(String payload, ref NetworkStream ns)
        {
            Log(payload);
            String[] commands = payload.Split(new Char[] { '|' }, StringSplitOptions.RemoveEmptyEntries);

            foreach (String command in commands)
            {
                if (command.Contains("emu_nes+"))
                {
                    String[] item = command.Split(new Char[] { '+' });
                    try
                    {
                        Process.Start(Settings.Default.emu_nes, String.Format(Settings.Default.nes_args, item[1]));
                    }
                    catch (Exception e)
                    {
                        Process.Start(Settings.Default.emu_nes, String.Format("\"{0}\"", item[1]));
                    }

                    break;
                }

                if (command.Contains("emu_snes+"))
                {
                    String[] item = command.Split(new Char[] { '+' });
                    try
                    {
                        Process.Start(Settings.Default.emu_snes, String.Format(Settings.Default.snes_args, item[1]));
                    }
                    catch (Exception e)
                    {
                        Process.Start(Settings.Default.emu_snes, String.Format("\"{0}\"", item[1]));
                    }

                    break;
                }
                if (command.Contains("emu_gba+"))
                {
                    String[] item = command.Split(new Char[] { '+' });
                    try
                    {
                        Process.Start(Settings.Default.emu_gba, String.Format(Settings.Default.gba_args, item[1]));
                    }
                    catch (Exception e)
                    {
                        Process.Start(Settings.Default.emu_gba, String.Format("\"{0}\"", item[1]));
                    }

                    break;
                }

                if (command.Contains("emu_n64+"))
                {
                    String[] item = command.Split(new Char[] { '+' });
                    try
                    {
                        Process.Start(Settings.Default.emu_n64, String.Format(Settings.Default.n64_args, item[1]));
                    }
                    catch (Exception e)
                    {
                        Process.Start(Settings.Default.emu_n64, String.Format("\"{0}\"", item[1]));
                    }

                    break;
                }

                if (command.Contains("emu_psx+"))
                {
                    String[] item = command.Split(new Char[] { '+' });
                    try
                    {
                        Process.Start(Settings.Default.emu_psx, String.Format(Settings.Default.psx_args, item[1]));
                    }
                    catch (Exception e)
                    {
                        Process.Start(Settings.Default.emu_psx, String.Format("\"{0}\"", item[1]));
                    }

                    break;
                }

                switch (command.Trim())
                {
                    case "reload_config":
                        Settings.Default.Reload();
                        break;
                    case "restart":
                        Application.Restart();
                        break;
                    case "computer_suspend":
                        Application.SetSuspendState(PowerState.Suspend, false, false);
                        break;
                    case "computer_hibernate":
                        Application.SetSuspendState(PowerState.Hibernate, false, false);
                        break;
                    case "computer_shutdown":
                        Process.Start("ShutDown", "/s");
                        break;
                    case "computer_restart":
                        Process.Start("ShutDown", "/r");
                        break;
                    case "files_n64":
                        Send(FetchFiles(Settings.Default.n64_rom_folders, Settings.Default.n64_rom_filters), ref ns);
                        break;
                    case "files_psx":
                        Send(FetchFiles(Settings.Default.psx_rom_folders, Settings.Default.psx_rom_filters), ref ns);
                        break;
                    case "files_nes":
                        Send(FetchFiles(Settings.Default.nes_rom_folders, Settings.Default.nes_rom_filters), ref ns);
                        break;
                    case "files_snes":
                        Send(FetchFiles(Settings.Default.snes_rom_folders, Settings.Default.snes_rom_filters), ref ns);
                        break;
                    case "files_gba":
                        Send(FetchFiles(Settings.Default.gba_rom_folders, Settings.Default.gba_rom_filters), ref ns);
                        break;
                    default:
                        SendKeys.SendWait(command);
                        break;
                }
            }
        }

        private static void Send(String message, ref NetworkStream ns)
        {
            Byte[] data;
            try
            {
                data = Encoding.UTF8.GetBytes(message.Trim());
                ns.Write(data, 0, data.Length);
                ns.Close();
            }
            catch (Exception e)
            {
                Log(e.Message);
            }
        }

        private static String FetchFiles(String[] FolderArgs, String[] FilterArgs)
        {
            StringBuilder sb = new StringBuilder();

            foreach (var folder in FolderArgs)
            {
                Log(folder);
                if(Directory.Exists(folder))
                foreach (var filter in FilterArgs)
                    foreach (var item in Directory.GetFiles(folder, filter, SearchOption.TopDirectoryOnly))
                        sb.Append(String.Format("{0}{1}", item, "|"));
            }
            return sb.ToString().Trim();

        }

        public static void Log(String message)
        {
            using (var log = new StreamWriter(Program.LogFile,true,Encoding.UTF8))
                log.WriteLine(String.Format("{0}:\t{1}",DateTime.Now,message));
        }

    }
}
