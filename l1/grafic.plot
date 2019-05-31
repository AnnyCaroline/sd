reset
set encoding utf8
fontsize = 18
set term postscript enhanced eps color fontsize
set boxwidth 0.9
set style fill solid 1.00 border 0
set style histogram errorbars gap 3 lw 1
set style data histograms
set xlabel "Tamanho dos pacotes (bytes)"
set bars 0.6
set datafile separator ","

set terminal postscript eps
set output "out.eps"

set title "Teste"
set ylabel "RTT (s)"
set yrange [0 : 100]
plot 'out.dat' \
		   using 2:3:4:xtic(1) ti "" linecolor rgb "#FF0000" fs pattern 2, \

set terminal pngcairo
set output "out.png"
replot
