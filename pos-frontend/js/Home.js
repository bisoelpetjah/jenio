import React, { Component } from 'react'
import { Link } from 'react-router'
import Section from 'grommet/components/Section'

import Catalog from './Catalog'
import Cart from './Cart'
import PaymentModal from './PaymentModal'
import JenioInitModal from './JenioInitModal'
import JenioSuccessModal from './JenioSuccessModal'
import JenioFailedModal from './JenioFailedModal'

import { catalog } from './merchantData'

export default class Home extends Component {
    constructor() {
        super()

        this.state = {
            showPaymentModal: false,
            showJenioInitModal: false,
            showJenioSuccessModal: false,
            showJenioFailedModal: false,
            balance_before: null,
            balance_after: null,
        }

        this.togglePaymentModal = this.togglePaymentModal.bind(this)
        this.toggleJenioInitModal = this.toggleJenioInitModal.bind(this)
        this.toggleJenioSuccessModal = this.toggleJenioSuccessModal.bind(this)
        this.toggleJenioFailedModal = this.toggleJenioFailedModal.bind(this)
    }

    togglePaymentModal() {
        this.setState({ showPaymentModal: !this.state.showPaymentModal })
    }

    toggleJenioInitModal() {
        this.setState({ showJenioInitModal: !this.state.showJenioInitModal })
    }

    toggleJenioSuccessModal() {
        this.setState({ showJenioSuccessModal: !this.state.showJenioSuccessModal })
    }

    setBalance(balance_before, balance_after) {
        this.setState({ balance_before, balance_after })
    }

    toggleJenioFailedModal() {
        this.setState({ showJenioFailedModal: !this.state.showJenioFailedModal })
    }

    render() {
        return (
            <Section
                direction='row'
                pad='small'
                colorIndex='light-2'>
                <Catalog />
                <Cart togglePaymentModal={this.togglePaymentModal} />
                {
                    this.state.showPaymentModal ?
                        <PaymentModal
                            togglePaymentModal={this.togglePaymentModal}
                            toggleJenioInitModal={this.toggleJenioInitModal} />
                        : null
                }
                {
                    this.state.showJenioInitModal ?
                        <JenioInitModal
                            toggleJenioInitModal={this.toggleJenioInitModal}
                            toggleJenioSuccessModal={this.toggleJenioSuccessModal}
                            setBalance={(balance_before, balance_after) => { this.setBalance(balance_before, balance_after) }}
                            toggleJenioFailedModal={this.toggleJenioFailedModal} />                            
                        : null
                }
                {
                    this.state.showJenioSuccessModal ?
                        <JenioSuccessModal
                            balance_before={this.state.balance_before}
                            balance_after={this.state.balance_after}
                            toggleJenioSuccessModal={this.toggleJenioSuccessModal} />
                        : null
                }
                {
                    this.state.showJenioFailedModal ?
                        <JenioFailedModal
                            toggleJenioFailedModal={this.toggleJenioFailedModal} />
                        : null
                }
            </Section>
        )
    }
}
