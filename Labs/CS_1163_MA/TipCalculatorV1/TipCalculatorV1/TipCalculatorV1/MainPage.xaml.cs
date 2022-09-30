using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace TipCalculatorV1
{
    public partial class MainPage : ContentPage
    { 
        public MainPage()
        {
            InitializeComponent();
        }

        private void FifteenPercent_Clicked(object sender, EventArgs e)
        {
            var button = sender as Button;
            doWork(DinnerTotal.Text, 0.15m);
        }

        private void TwentyPercet_Clicked(object sender, EventArgs e)
        {
            var button = sender as Button;
            doWork(DinnerTotal.Text, 0.20m);
        }

        private void TenPercent_Clicked(object sender, EventArgs e)
        {
            var button = sender as Button;
            doWork(DinnerTotal.Text, 0.10m);
        }

       
        private void clearFields(params Entry[] fields)
        {
            foreach (var field in fields)
            {
                field.Text = "";
            }
            
        }

        async private void doWork(String input, decimal percentage)
        {
       
            if (input == null || input.Length == 0)
            {
                clearFields(DinnerTotal, TipTotal, FinalTotal);
                await DisplayAlert("Blank Field", "Your Dinner Bill field is blank", "Go Back");
                return;
            }

            if (input[0] == '$')
            {
                input = input.Substring(1, input.Length-1);
            }

            if (decimal.TryParse(input, out decimal c))
            {
                var TipTotalCalculation = c * percentage;
                var Total = TipTotalCalculation + c;
                TipTotal.Text = TipTotalCalculation.ToString("$#,###.00");
                FinalTotal.Text = Total.ToString("$#,###.00");
                DinnerTotal.Text = c.ToString("$#,###.00");
            }
            else
            {
                await DisplayAlert("Incorrect Vaue", "You have placed an incorrect value in the Dinner Bill field", "Go Back");
                clearFields(DinnerTotal, TipTotal);
            }
        }

    }
}
