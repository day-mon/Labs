using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace Chuck
{
    public partial class MainPage : ContentPage
    {
        private WebHandler Handler = WebHandler.GetInstance();

        public MainPage()
        {
            InitializeComponent();
        }

        async private void AddButton_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new AddEditScreen(false, -1));
        }


        async protected override void OnAppearing()
        {
            this.IsBusy = true;
            this.IsEnabled = false;
            this.Indicator.IsRunning = true;
            var List = await Handler.GetObservableList();


            if (List.Count == 0)
            {
                this.HaveQuotes.IsVisible = true;
                this.IsEnabled = true;
                this.IsBusy = false;
                this.Indicator.IsRunning = false;
                return;
            }


            this.MainPageListView.ItemsSource = List;
            this.IsBusy = false;
            this.HaveQuotes.IsVisible = false;
            this.Indicator.IsRunning = false;
            this.IsEnabled = true;
        }


        async private void MainPageListView_ItemTapped(object sender, ItemTappedEventArgs e)
        {
           await Navigation.PushAsync(new AddEditScreen(true, e.ItemIndex));
        }
    }
}
