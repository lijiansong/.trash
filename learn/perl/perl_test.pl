#!/home/js-lee/Desktop/perl -w
print "hello,world\n";
print 2*10 . "\n";
print "12test"*2 ."\n";
$line=<STDIN>;
if($line eq "\n")
{
print "blank line!\n";
}
else
{
print "the input is: $line";
}
