ccode=$(cat /tmp/system.cfg)
case $ccode in
*countrycode=511*)
	echo "ja esta em ct"
;;
*)
	echo "nao esta em ct"
;;
esac
