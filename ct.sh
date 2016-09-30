touch /etc/persistent/ct
/bin/sed -ir '/radio.1.countrycode/ c radio.1.countrycode=511' /tmp/system.cfg
/bin/sed -ir '/radio.countrycode/ c radio.countrycode=511' /tmp/system.cfg
save
reboot
