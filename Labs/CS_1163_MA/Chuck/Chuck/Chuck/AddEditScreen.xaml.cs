using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Chuck
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class AddEditScreen : ContentPage
    {
        private WebHandler Handler = WebHandler.GetInstance();
        private int index;

        public AddEditScreen(bool isDeleteViewAble, int index)
        {
            InitializeComponent();
            this.index = index;
            this.DeleteButton.IsVisible = isDeleteViewAble;

            if (isDeleteViewAble)
            {
               Quote.Text =  Handler.GetCachedList()[index].ChuckQuote;
            }
        }

        async private void SaveButton_Clicked(object sender, EventArgs e)
        {
            var check = checks();
            if (!String.IsNullOrEmpty(check))
            {
                await DisplayAlert("Error", check, "OK");
                return;
            }

            this.IsBusy = true;
            this.IsEnabled = false;
            bool result;

            if (DeleteButton.IsVisible)
            {
                var Model = Handler.GetCachedList()[index];
                var EditedText = Quote.Text;
                result = await Handler.SendPutAsync(Model, EditedText);
            }
            else
            {
                var chuck = new ChuckModel();
                chuck.ChuckQuote = Quote.Text;
                result = await Handler.SendPostAsync(chuck);
            }
            this.IsEnabled = true;
            this.IsBusy = false;

            if (!result)
                await DisplayAlert("Error", "Could not send request to api.. Please try again", "Ok");
            

            await Navigation.PopAsync();

        }

        async private void DeleteButton_Clicked(object sender, EventArgs e)
        {
            var element = Handler.GetCachedList()[index];
            var successful = await Handler.SendDeleteAsync(element);

            if (!successful)
            {
                await DisplayAlert("Error", "Could not send request to api.. Please try again", "Ok");
            }

            await Navigation.PopAsync();

        }

        private string checks()
        {

            var Builder = new StringBuilder();
            if (String.IsNullOrEmpty(Quote.Text))
            {
                Builder.Append("You cannot leave your quote field empty!");
            }

            //added just incase there are more cases

            return Builder.ToString();
        }

        async private void BackButton_Clicked(object sender, EventArgs e)
        {
            await Navigation.PopAsync();
        }
    }
}