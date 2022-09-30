using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Chuck
{
    public partial class App : Application
    {
        private WebHandler handler = WebHandler.GetInstance();
        public App()
        {
            InitializeComponent();

            MainPage = new NavigationPage(new MainPage());
        }

        protected override void OnStart()
        {
       
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}
