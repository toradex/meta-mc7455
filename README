How to add support for the Sierra Wirless MC7455 modem

To build the Image
1. Add meta-mc7455 to your layers directory
2. Add the following lines to your local.conf:
  IMAGE_INSTALL_append = " modemmanager networkmanager"
  IMAGE_INSTALL_remove = " ofono connman connman-gnome connman-client"
  DISTRO_FEATURES_remove = " 3g" # To finally remove ofono
3. Add the following layer to bblayers.conf
  ${TOPDIR}/../layers/meta-mc7455
3. Recompile angstrom-lxde-image
4. Install the newly created image

On the module we can use ModemManager and NetworkManager to connect to a network and to receive GPS information

# Say no to auto connect if the SIM has a pin
nmcli c add type gsm ifname cdc-wdm0 con-name <connection name> apn <APN> connection.autoconnect no
# Now it will ask you if you want to set a pin
nmcli c --ask up <connection name>
# You can change the connection with (e.g. set autoconnect to yes now)
nmcli c edit <conneciton name>

For debugging we can use ModemManager directly. Connect to network:
mmcli -i 0 --pin <pin>
mmcli -m 0 -e
mmcli -m 0 --simple-connect="apn=<APN>"

Receiving GPS data:
mmcli -m 0 --location-enable-gps-raw --location-enable-gps-nmea
mmcli -m 0 --location-get
mmcli -m 0 --location-status

