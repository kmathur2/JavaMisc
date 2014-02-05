use POSIX qw( strftime );
my $formatted = strftime("%Y-%m-%d %H:%M:%S", localtime(1387243806496/1000));
open (MYFILE, 'kartik_results.txt');

open (TWITTER, '>> sos.csv');
print TWITTER "Handle,Time stamp,Post\n";
 while (<MYFILE>){
  #print $_;
  my @elements=split(',',$_);
  my @handle=split(' ',$elements[0]);
  my @time=split('=',$elements[1]);
  my $readableTime=strftime("%Y-%m-%d %H:%M:%S", localtime($time[1]/1000));
  print TWITTER $handle[0].",".$readableTime.",".$elements[2];
  my $string=$elements[2];
}
close(TWITTER);
close (MYFILE); 


#print "\n$formatted";
